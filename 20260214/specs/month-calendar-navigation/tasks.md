# Tasks: 월간 달력 기본 보기 및 이동

## Goal

Outside-in + TDD로 월간 달력 기본 표시와 월 이동/오늘 이동 기능을 구현한다.

## Current Phase

🔄 Phase 3: Implementation

## Phases

### Phase 1: Requirements & Discovery ✅

- [x] 요구사항 정의
- [x] 스펙 문서 작성 (spec.md)
- [x] 스펙 리뷰 및 승인

### Phase 2: Planning & Structure ✅

- [x] 구현 계획 작성 (plan.md)
- [x] 테스트 기준(ATDD 관점)을 plan에 반영했는지 재확인
- [x] 프로젝트 초기 설정 (Vite/TS/Tailwind/Vitest/Prettier)

### Phase 3: Implementation 🔄

- [x] App에서 MonthCalendar 연결 (Red/Green/Refactor)
- [x] MonthCalendar 기본 렌더 (Red/Green/Refactor)
- [x] CalendarHeader 버튼 동작 (Red/Green/Refactor)
- [x] CalendarGrid 달력 그리드 (Red/Green/Refactor)
- [x] 달력 계산 유틸 (Red/Green/Refactor)

### Phase 4: Testing ⏸️

- [x] 전체 빌드 확인
- [ ] 전체 테스트 통과 확인
- [ ] 수동 테스트

## Key Questions

1. 오늘 강조 스타일을 어떤 시각적 규칙으로 통일할까?
2. 달력 그리드의 주 시작 요일은 무엇을 기준으로 할까?
3. 오늘 버튼 위치와 라벨은 어떤 기준이 가장 직관적인가?

## Decisions Made

| Decision                           | Rationale                                     |
| ---------------------------------- | --------------------------------------------- |
| Outside-in 순서로 App부터 진행     | 사용자 흐름을 먼저 통과시키기 위함            |
| TDD는 각 단계 Red/Green/Refactor로 | 변경 안정성과 리팩터링 용이성을 확보하기 위함 |

## Errors Encountered

| Error | Attempt | Resolution |
| ----- | ------- | ---------- |
| -     | -       | -          |

## Notes

- 진행할 때마다 Phase 상태를 업데이트하세요: ⏸️ 대기 → 🔄 진행 중 → ✅ 완료
- 중요한 결정을 내리기 전에 이 계획을 다시 읽어보세요. (attention manipulation)
- 모든 오류를 기록하세요. 삽질을 반복하는 걸 막을 수 있습니다.
- Red-Green-Refactoring은 하나의 작은 사이클로 자주 반복하세요.
