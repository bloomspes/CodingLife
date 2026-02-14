# Progress Log

> **각 단계를 완료하거나 문제가 발생하면 업데이트하세요.**

## Session 2026-02-14

### Phase 1: Requirements & Discovery ✅

**작업 내역**:

1. 달력 기본 보기/이동 요구사항 정리.
2. spec.md에 사용자 시나리오와 요구사항 문서화.

**생성/수정 파일**:

- `specs/month-calendar-navigation/README.md` (작성)
- `specs/month-calendar-navigation/spec.md` (작성)

### Phase 2: Planning & Structure ✅

**작업 내역**:

1. Outside-in 순서로 plan.md 구현 단계 정리.
2. TDD/ATDD 관점 보강.
3. 프로젝트 초기 설정 (Vite/TS/Tailwind/Vitest/Prettier).
4. 모호한 체크박스(초기 컴포넌트/유틸 구조 확정) 제거.

**생성/수정 파일**:

- `specs/month-calendar-navigation/plan.md` (작성/수정)
- `package.json` (스크립트/의존성 설정)
- `pnpm-lock.yaml` (의존성 잠금 파일)
- `vite.config.ts` (Vite/테스트 설정)
- `tsconfig.app.json` (테스트 타입 추가)
- `src/index.css` (Tailwind 초기화)
- `src/test/setup.ts` (Testing Library 초기 설정)
- `.nvmrc` (Node 버전 고정)

### Phase 3: Implementation 🔄

**작업 내역**:

1. App에서 MonthCalendar 연결 (Outside-in 첫 단계).
2. MonthCalendar 기본 렌더 (연월, 요일, 오늘 강조 표시).
3. CalendarHeader 버튼 동작 (이전/다음/오늘 월 이동).
4. CalendarGrid 컴포넌트 분리.
5. 달력 계산 유틸 분리 (월 라벨, 이동, 그리드 생성).
6. 접근성과 SEO를 위해 문서 언어 설정을 한국어로 변경.

**생성/수정 파일**:

- `src/App.tsx` (MonthCalendar 연결)
- `src/components/MonthCalendar.tsx` (초기 뼈대 추가)
- `src/App.test.tsx` (App 렌더링 테스트 추가)
- `src/components/__tests__/MonthCalendar.test.tsx` (MonthCalendar 기본 렌더
  테스트)
- `src/components/MonthCalendar.tsx` (월 이동 상태/핸들러 추가)
- `src/components/CalendarGrid.tsx` (달력 그리드 분리)
- `src/lib/calendar.ts` (달력 계산 유틸)
- `src/lib/date.ts` (날짜 유틸)
- `src/lib/__tests__/calendar.test.ts` (달력 유틸 테스트)
- `index.html` (lang 속성 ko로 변경)

### Phase 4: Testing ✅

**작업 내역**:

1. 전체 빌드 확인 (`pnpm build`).
2. 전체 테스트 통과 확인 (`pnpm test`).
3. Playwright로 수동 테스트 자동화 실행 (`pnpm test:e2e`).

**생성/수정 파일**:

- `playwright.config.ts` (E2E 테스트 설정)
- `tests/e2e/month-calendar.spec.ts` (수동 테스트 시나리오)
- `vite.config.ts` (dev 서버 포트 고정)
- `package.json` (E2E 스크립트 추가)

## Test Results

| Test                      | Input | Expected | Actual                            | Status |
| ------------------------- | ----- | -------- | --------------------------------- | ------ |
| 달력 렌더링/이동          | -     | Pass     | Pass (Vitest 6 tests)             | ✅     |
| 달력 유틸                 | -     | Pass     | Pass (Vitest 3 tests)             | ✅     |
| 전체 테스트               | -     | Pass     | Pass (Vitest 9 tests)             | ✅     |
| 수동 테스트 시나리오(E2E) | -     | Pass     | Pass (Playwright 3 tests x3 runs) | ✅     |
| 전체 빌드                 | -     | Pass     | Pass (Vite build succeeded)       | ✅     |

## Error Log

| Timestamp  | Error                               | Attempt | Resolution                    |
| ---------- | ----------------------------------- | ------- | ----------------------------- |
| 2026-02-14 | vitest가 Playwright 스펙을 로드함   | 1       | 검증을 `pnpm test:e2e`로 수행 |
| 2026-02-14 | No test files found (vitest exit 1) | 3       | 해결됨 (테스트 추가 후 통과)  |
| 2026-02-14 | MonthCalendar 버튼 동작 테스트 실패 | 1       | 해결됨 (상태/핸들러 추가)     |
| 2026-02-14 | Vite build 타입 오류 (test config)  | 1       | 해결됨 (vitest/config 사용)   |
| 2026-02-14 | MonthCalendar 기본 렌더 테스트 실패 | 1       | 해결됨 (MonthCalendar 구현)   |
| 2026-02-14 | Playwright webServer 대기 지연      | 1       | 해결됨 (dev 서버 포트 고정)   |

## 5-Question Reboot Check

| Question               | Answer                        |
| ---------------------- | ----------------------------- |
| 1. 현재 어느 단계인가? | Phase 4 (완료)                |
| 2. 다음에 할 일은?     | 작업 완료 확인 및 공유        |
| 3. 목표는?             | 월간 달력 기본 보기/이동 구현 |
| 4. 지금까지 배운 것?   | findings.md 참고              |
| 5. 완료한 작업은?      | 위 Phase 1/2 작업 내역 참고   |
