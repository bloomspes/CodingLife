# Implementation Plan: 월간 달력 기본 보기 및 이동

## Summary

Outside-in으로 App부터 연결한 다음, 주요 컴포넌트와 달력 계산 유틸을 순차적으로
채운다.

## Requirements

1. 오늘 기준 이번 달 달력 표시 및 오늘 강조.
2. 연월 표시와 이전/다음 달 이동 버튼 제공.
3. 오늘 버튼으로 언제든 오늘 달로 복귀.

## Critical Files

### New Files

- `src/components/MonthCalendar.tsx`
- `src/components/CalendarHeader.tsx`
- `src/components/CalendarGrid.tsx`
- `src/lib/calendar.ts`
- `src/lib/date.ts`
- `src/lib/__tests__/calendar.test.ts`
- `src/components/__tests__/MonthCalendar.test.tsx`

### Modified Files

- `src/App.tsx`
- `src/index.css`

### Reference Files

- `specs/month-calendar-navigation/spec.md`

## Architecture

### User Flow

```text
사용자 → [이전/다음/오늘 클릭] → MonthCalendar 상태 변경
                          ↓
                  CalendarHeader 표시 갱신
                          ↓
                  CalendarGrid 달력 렌더링
```

### Event Flow

```text
1. UI: 버튼 클릭
2. 상태: 기준 월 변경
3. 계산: 월 달력 데이터 생성
4. 렌더: 연월 및 날짜 그리드 업데이트
```

### Domain Model

```text
MonthView
├── baseDate (현재 기준 날짜)
├── monthMatrix (달력 행렬)
└── helpers
    ├── getMonthLabel()
    ├── goPrevMonth()
    ├── goNextMonth()
    └── goToday()
```

## Implementation Steps

### Step 0: ATDD 시나리오 스펙 합의

**Red:** `specs/month-calendar-navigation/spec.md`

```text
// 사용자 시나리오와 수용 기준을 명확히 하고, 구현 전에 테스트 관점으로 재확인
```

**Green:** `specs/month-calendar-navigation/spec.md`

```text
// 시나리오가 애매하면 문장과 조건을 보완
```

**Refactoring:** 중복 표현과 범위 과장을 제거

### Step 1: App에서 MonthCalendar 연결

**Red:** `src/App.tsx`

```tsx
// App에서 MonthCalendar 렌더 테스트
```

**Green:** `src/App.tsx`

```tsx
// App에 MonthCalendar 연결
```

**Refactoring:** App 레이아웃 정리

### Step 2: MonthCalendar 기본 화면 렌더링

**Red:** `src/components/__tests__/MonthCalendar.test.tsx`

```tsx
// 초기 렌더 시 오늘 달 표시, 연월 표시, 오늘 강조 테스트
```

**Green:** `src/components/MonthCalendar.tsx`

```tsx
// 최소 구현으로 테스트 통과
```

**Refactoring:** 표시/레이아웃 책임 분리

### Step 3: CalendarHeader 버튼 동작

**Red:** `src/components/__tests__/MonthCalendar.test.tsx`

```tsx
// 이전/다음 버튼 월 이동, 오늘 버튼 복귀 테스트
```

**Green:** `src/components/CalendarHeader.tsx`

```tsx
// 버튼 핸들러 연결
```

**Refactoring:** 헤더 표시/동작 분리

### Step 4: CalendarGrid 달력 그리드

**Red:** `src/components/__tests__/MonthCalendar.test.tsx`

```tsx
// 월간 그리드 렌더링과 빈 칸 채우기 테스트
```

**Green:** `src/components/CalendarGrid.tsx`

```tsx
// 달력 그리드 최소 구현
```

**Refactoring:** 스타일과 구조 정리

### Step 5: 달력 데이터 계산 정교화

**Red:** `src/lib/__tests__/calendar.test.ts`

```ts
// 월 그리드 계산, 시작 요일 정렬, 오늘 포함 여부 테스트
```

**Green:** `src/lib/calendar.ts`

```ts
// 월 그리드 생성 로직 최소 구현
```

**Refactoring:** 날짜 유틸 분리

## Verification

### Build

```bash
pnpm build
```

### Test

```bash
pnpm test
```

### Manual Test

1. 앱 시작 시 오늘 달력 및 오늘 강조 확인.
2. 이전/다음 버튼 클릭 시 월 이동 확인.
3. 오늘 버튼 클릭 시 오늘 달로 복귀 확인.

## Considerations

### UI 접근성

- 버튼은 텍스트 또는 aria-label로 목적을 설명한다.

### 날짜 계산 정확성

- 월 시작 요일과 말일 계산이 정확해야 한다.
