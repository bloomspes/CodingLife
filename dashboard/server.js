const express = require("express");
const cors = require("cors");
const fs = require("fs");
const path = require("path");

const app = express();
const PORT = 7890;

app.use(cors());
app.use(express.json());

// ==========================================
// 1. Load Scenario Data
// ==========================================
const scenarioPath = path.join(__dirname, "scenario.json");
let scenarioData = { robots: [], events: [], logs: [] };

try {
  const data = fs.readFileSync(scenarioPath, "utf8");
  scenarioData = JSON.parse(data);
  const { meta } = scenarioData;
  console.log(`✅ Loaded scenario: "${meta.scenarioName}"`);
  console.log(
    `   📊 ${scenarioData.robots.length} robots, ${scenarioData.events.length} events, ${scenarioData.logs.length} logs`,
  );
  console.log(
    `   ⏱️  Total duration: ${meta.totalDuration}ms (${(meta.totalDuration / 1000 / 60).toFixed(1)} min)`,
  );
} catch (err) {
  console.error("❌ Failed to load scenario.json:", err.message);
  process.exit(1);
}

// In-Memory DB 초기화 (새로고침 시 초기 상태 복구됨)
// 주의: 서버를 껐다 켜지 않는 이상 메모리 상의 robots는 계속 변경됨.
// 완벽한 테스트를 위해 GET /robots 요청 시마다 초기 상태를 줄지,
// 아니면 메모리 상태를 유지할지 결정해야 함.
// 여기서는 "서버 실행 시점" 기준으로 초기화하고 변경 사항을 누적합니다.
let currentRobots = JSON.parse(JSON.stringify(scenarioData.robots));

// ==========================================
// 2. REST API
// ==========================================

app.get("/robots", (req, res) => {
  // 네트워크 지연 500ms 고정
  setTimeout(() => {
    // 일관된 에러 테스트를 위해 특정 헤더나 쿼리가 있을 때만 에러를 낼 수도 있지만,
    // 여기서는 간단히 성공 응답만 보냅니다. (랜덤 에러 제거)
    res.json({ robots: currentRobots });
  }, 500);
});

app.get("/robots/:id", (req, res) => {
  const robot = currentRobots.find((r) => r.id === req.params.id);
  if (!robot) return res.status(404).json({ error: "Robot not found" });
  res.json(robot);
});

// ==========================================
// 3. SSE Implementation
// ==========================================

const setSSEHeaders = (res) => {
  res.setHeader("Content-Type", "text/event-stream");
  res.setHeader("Cache-Control", "no-cache");
  res.setHeader("Connection", "keep-alive");
};

// Global Events Stream (Play Scenario)
app.get("/events/stream", (req, res) => {
  setSSEHeaders(res);
  console.log("🟢 [SSE] Client connected to /events/stream");

  // Send metadata as first message
  const metaPayload = {
    type: "SCENARIO_META",
    data: scenarioData.meta,
    timestamp: new Date().toISOString(),
  };
  res.write(`event: scenario_meta\n`);
  res.write(`data: ${JSON.stringify(metaPayload)}\n\n`);

  // 연결 유지
  const keepAlive = setInterval(() => {
    res.write(": keep-alive\n\n");
  }, 15000);

  // 시나리오 이벤트 재생
  // 클라이언트가 연결된 시점(t=0)부터 시나리오의 delta(또는 기존 delay)에 맞춰 이벤트를 순차적으로 전송합니다.
  let accumulatedDelay = 0;
  const timeouts = scenarioData.events.map((event) => {
    const scheduleDelay =
      event.delta != null ? (accumulatedDelay += event.delta) : event.delay; // 호환성: delta가 없으면 기존 delay 사용
    return setTimeout(() => {
      // 1. 메모리 DB 업데이트 (상태 동기화를 위해)
      if (event.type === "ROBOT_UPDATED") {
        const target = currentRobots.find((r) => r.id === event.data.robotId);
        if (target) Object.assign(target, event.data.updates);
      } else if (event.type === "ROBOT_DELETED") {
        currentRobots = currentRobots.filter(
          (r) => r.id !== event.data.robotId,
        );
      } else if (event.type === "ROBOT_ADDED") {
        // NOW를 현재 시간으로 치환
        const newRobot = { ...event.data.robot };
        if (newRobot.lastActive === "NOW")
          newRobot.lastActive = new Date().toISOString();
        currentRobots.push(newRobot);
      }

      // 2. 이벤트 전송
      const eventPayload = {
        ...event,
        timestamp: new Date().toISOString(),
      };

      // event.data 내부의 NOW 치환 (ADDED 이벤트용)
      if (
        eventPayload.data.robot &&
        eventPayload.data.robot.lastActive === "NOW"
      ) {
        eventPayload.data.robot.lastActive = eventPayload.timestamp;
      }

      res.write(`event: ${event.type.toLowerCase()}\n`);
      res.write(`data: ${JSON.stringify(eventPayload)}\n\n`);
    }, scheduleDelay);
  });

  // 시나리오가 완료되면 스트림 종료
  const totalDuration = scenarioData.events.reduce((sum, e) => {
    if (typeof e.delta === "number") return sum + e.delta;
    if (typeof e.delay === "number") return Math.max(sum, e.delay); // 기존 delay와 혼용 시 최대값 사용
    return sum;
  }, 0);
  const endTimeout = setTimeout(() => {
    console.log("🏁 [SSE] Scenario completed, closing stream");
    clearInterval(keepAlive);
    res.end();
  }, totalDuration + 1000);

  req.on("close", () => {
    console.log("🔴 [SSE] Client disconnected from /events/stream");
    clearInterval(keepAlive);
    clearTimeout(endTimeout);
    timeouts.forEach(clearTimeout); // 연결 끊기면 예정된 이벤트 취소
  });
});

// Logs Stream (Play Scenario)
app.get("/robots/:id/logs/stream", (req, res) => {
  const { id } = req.params;

  // Validate robot exists
  const robot = currentRobots.find((r) => r.id === id);
  if (!robot) {
    console.log(`❌ [SSE] Log stream not found for ${id}`);
    return res.status(404).json({ error: `Robot "${id}" not found` });
  }

  setSSEHeaders(res);
  console.log(`🟢 [SSE] Log stream started for ${id} (${robot.name})`);

  // Filter logs for specific robot (include system logs)
  const filteredLogs = scenarioData.logs.filter(
    (log) => log.robotId === id || log.robotId === "system",
  );
  console.log(`   📝 ${filteredLogs.length} logs available for this robot`);

  // Send metadata as first message
  const logMetaPayload = {
    type: "LOG_STREAM_META",
    data: {
      robotId: id,
      robotName: robot.name,
      totalLogs: filteredLogs.length,
    },
    timestamp: new Date().toISOString(),
  };
  res.write(`event: log_stream_meta\n`);
  res.write(`data: ${JSON.stringify(logMetaPayload)}\n\n`);

  // 시나리오 로그 재생
  let accumulatedLogDelay = 0;
  const timeouts = filteredLogs.map((log) => {
    const scheduleDelay =
      log.delta != null ? (accumulatedLogDelay += log.delta) : log.delay; // 호환성: delta가 없으면 기존 delay 사용
    return setTimeout(() => {
      const logPayload = {
        timestamp: new Date().toISOString(),
        level: log.level,
        robotId: log.robotId,
        message: log.message,
      };
      res.write(`event: log\n`);
      res.write(`data: ${JSON.stringify(logPayload)}\n\n`);
    }, scheduleDelay);
  });

  // 시나리오가 끝나면 "done" 이벤트 전송 (옵션)
  const totalLogDuration = filteredLogs.reduce((sum, l) => {
    if (typeof l.delta === "number") return sum + l.delta;
    if (typeof l.delay === "number") return Math.max(sum, l.delay); // 혼용 시 최대값
    return sum;
  }, 0);
  const doneTimeout = setTimeout(() => {
    res.write(`event: done\n`);
    res.write(`data: {"message": "Stream ended", "robotId": "${id}"}\n\n`);
    console.log(`🏁 [SSE] Log stream completed for ${id}`);
  }, totalLogDuration + 1000);

  req.on("close", () => {
    console.log(`🔴 [SSE] Log stream stopped for ${id}`);
    timeouts.forEach(clearTimeout);
    clearTimeout(doneTimeout);
  });
});

app.listen(PORT, () => {
  console.log(`\n🚀 Robot Monitoring Server running on port ${PORT}`);
  console.log(`📂 Scenario loaded from ${scenarioPath}`);
  console.log(
    `\n[Endpoints]\n` +
      `- GET /robots                   (Returns current robots state)\n` +
      `- GET /robots/:id              (Returns specific robot by id)\n` +
      `- GET /events/stream           (Plays scenario events + metadata)\n` +
      `- GET /robots/:id/logs/stream  (Plays robot-specific logs + system logs)\n`,
  );
});
