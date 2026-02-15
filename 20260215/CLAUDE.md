# AI Agent Workflow Guide

## 워크플로우 개요

이 프로젝트는 File-based Planning Workflow를 따릅니다. 각 단계마다 사람의 검토를
받으며, 문서를 지속적으로 업데이트하고 커밋합니다.

**중요**: 사용자가 명시적으로 요청하지 않은 내용은 추측해서 문서에 넣지 않습니다.

## 워크플로우 단계

### Phase 1: README 작성

[README.md](README.md) 작성 → **사람 검토** ⏸️ → 피드백 반영 후 커밋:

- Background: 프로젝트의 필요성과 배경
- Goal: 달성하고자 하는 목표와 가치
- How it works: 동작 방식
- Related Documents: 관련 문서 링크

### Phase 2: Spec 작성

[spec.md](spec.md) 작성 → **사람 검토** ⏸️ → 피드백 반영 후 커밋 **(요구사항 중심, 기술 세부사항 제외)**:

- Overview: 핵심 기능 설명
- User Scenarios: As/I/So 형식의 사용자 스토리
- Functional Requirements: FR-1, FR-2... (MUST/SHOULD)
- Constraints: CON-1, CON-2... (제약사항)
- Success Criteria: SC-1, SC-2... (성공 기준)
- OpenAPI Specification: API 명세 (필요시)

### Phase 3: Plan 작성

[plan.md](plan.md) 작성 → **사람 검토** ⏸️ → 피드백 반영 후 커밋 **(기술/구현 중심 계획)**:

- Summary: 요구사항 요약
- Critical Files: 생성/수정/참조할 파일 목록
- Architecture: 아키텍처 다이어그램
- Step-by-Step Implementation: TDD 방식 구현 단계
- Verification: 빌드/테스트 명령어

### Phase 4: 실행 및 추적 🔄 (항상 작업하면서 실시간 갱신)

> **이 3개 파일은 Phase 1부터 끝까지 작업할 때마다 계속 갱신합니다.**
> 한 번 만들고 끝이 아니라, 모든 단계에서 살아있는 문서로 유지합니다.

**[tasks.md](tasks.md)** - 체크리스트 🔄 **단계 전환할 때마다 즉시 갱신**:

- Goal: 프로젝트 목표
- Current Phase: 현재 단계 (🔄/✅/⏸️)
- Phases: 4단계 체크리스트
- Key Questions: 미해결 질문
- Decisions Made: 결정사항 테이블
- Errors Encountered: 에러 로그

**[progress.md](progress.md)** - 진행 상황 🔄 **작업할 때마다 계속 추가**:

- Phase Management: 단계별 상태 추적
- 작업 세션 기록 (시간순, 최신이 아래)
- Test Results: 테스트 결과 테이블
- Error Log: 에러 로그
- 5-Question Reboot Check: 컨텍스트 복구

**[findings.md](findings.md)** - 프로젝트 MEMORY 🔄 **발견 즉시 실시간 갱신**:

- **Phase 1부터 시작**: 배운 점이 있으면 즉시 기록, 절대 나중으로 미루지 않음
- Requirements: 요구사항 체크리스트
- Research Findings: 코드베이스/패턴 분석
- Resources: 문서/파일/API 참조
- Technical Decisions: 기술적 결정 테이블
- Issues Encountered: 문제/원인/해결/결과
- Learnings: 타임스탬프 기반 학습 내용

## 핵심 원칙

### 1. 지속적 문서화

- 사고 과정을 끊임없이 기록
- 작은 발견도 즉시 [findings.md](findings.md)에 기록
- 각 작업 후 [progress.md](progress.md) 업데이트

### 2. 빈번한 커밋

- 의미 있는 변경사항마다 커밋
- 커밋 메시지는 명확하고 구체적으로
- 각 단계 완료 후 반드시 커밋

### 3. 문서 사이클 (작업 중 매번 자주 반복)

**업데이트 → 검토 → 동기화 → 재검토 → prettier → 커밋**

- 조금이라도 진행했으면 바로 실행, 몰아서 하지 않음
- prettier는 커밋 전 항상, 문서 여러 개 수정했을 때도 (`npx prettier --write *.md`)
- 커밋은 의미 있는 단위마다 즉시

### 4. 검토 기반 진행

- 각 주요 단계마다 사람의 검토 필수
- 검토 없이 다음 단계로 진행하지 않음
- 피드백을 적극 반영

### 5. 에러 학습

- 같은 실수 반복 방지
- 에러는 [findings.md](findings.md)와 [progress.md](progress.md) 양쪽에 기록
- 해결 방법과 학습 내용 명시

## AI Agent 실행 가이드

### 작업 시작 시

1. 관련 문서 읽기 (README, spec, plan, tasks, progress, findings)
2. 현재 단계 확인 ([tasks.md](tasks.md)의 Current Phase)
3. 이전 세션 내용 확인 ([progress.md](progress.md))
4. 미해결 질문 확인 ([tasks.md](tasks.md)의 Key Questions)

### 작업 중

작업을 조금이라도 진행했으면 아래 사이클을 즉시 실행:

**문서 사이클** (매번, 자주):

1. 문서 업데이트
   - 결정사항 → [tasks.md](tasks.md), [findings.md](findings.md)
   - 발견사항 → [findings.md](findings.md) 즉시 기록
   - 작업 내용 → [progress.md](progress.md)
2. 관련 문서 검토 (일관성 확인)
3. 동기화 — 변경사항이 있으면 관련 문서 모두 업데이트
4. 재검토 (동기화 후 확인)
5. `npx prettier --write *.md`
6. 커밋

> 이 사이클은 작업 중에 반복해서 실행합니다. 한꺼번에 몰아서 하지 않습니다.

### 작업 완료 후

1. 모든 문서 최신화 확인
2. 체크리스트 업데이트 ([tasks.md](tasks.md))
3. 세션 요약 작성 ([progress.md](progress.md))
4. 문서 사이클 한 번 더 실행 후 커밋
5. 다음 단계 준비 (필요시)

### 컨텍스트 복구 시

[progress.md](progress.md)의 5-Question Reboot Check 활용:

1. 현재 어느 단계에 있나?
2. 다음에 해야 할 작업은?
3. 이 프로젝트의 최종 목표는?
4. 지금까지 배운 교훈은?
5. 완료한 작업은?

## 문서 역할

### 계획 문서 (각 단계에서 사람 검토 후 확정)

- [README.md](README.md): 프로젝트 개요 (배경, 목표, 동작 방식)
- [spec.md](spec.md): **요구사항** - 무엇을 만들 것인가 (기술 X, 사용자 관점)
- [plan.md](plan.md): **기술/구현 계획** - 어떻게 만들 것인가 (아키텍처, TDD 단계)

### 실행 추적 문서 (항상 작업하면서 갱신)

- [findings.md](findings.md): **프로젝트 MEMORY** 🔄 — 사실상 AI의 기억. 발견, 결정, 학습을 즉시 기록
- [tasks.md](tasks.md): **체크리스트** 🔄 — 현재 단계와 할 일 목록. 단계 전환마다 즉시 갱신
- [progress.md](progress.md): **진행 상황** 🔄 — tasks와 맞추며 작업 흐름을 시간순으로 기록

## 체크리스트: 문서 작성 전

- [ ] 이전 문서들 읽었는가?
- [ ] 현재 단계를 정확히 파악했는가?
- [ ] 필요한 정보를 충분히 수집했는가?
- [ ] 미해결 질문이 있다면 명시했는가?

## 체크리스트: 문서 작성 후

- [ ] 모든 필수 섹션을 작성했는가?
- [ ] 다른 문서와 일관성이 있는가?
- [ ] 링크가 올바르게 연결되어 있는가?
- [ ] 커밋 메시지가 명확한가?
- [ ] 사람 검토가 필요한 단계인가?
