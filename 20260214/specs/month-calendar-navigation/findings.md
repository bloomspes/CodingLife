# Findings & Decisions

> **기술적 발견, 중요한 결정이 있을 때마다 이 파일을 즉시 업데이트하세요.**

## Requirements

- [ ] FR-1: 오늘 기준 이번 달 달력 표시.
- [ ] FR-2: 연월 표시 및 이전/다음 버튼 제공.
- [ ] FR-3: 오늘 버튼으로 오늘 달로 복귀.
- [ ] FR-4: 오늘 날짜 강조 표시.

## Research Findings

### 코드베이스 구조

- 아직 UI/도메인 코드 없음.

### 기존 패턴

- 아직 정의된 패턴 없음.

## Resources

### 문서

- [File-based Planning Workflow](https://github.com/ahastudio/file-based-planning-workflow)

### 코드 참조

- 설명: `specs/month-calendar-navigation/spec.md`

## Technical Decisions

| Decision                             | Rationale                               |
| ------------------------------------ | --------------------------------------- |
| 이번 기능은 클라이언트 UI만으로 구현 | API가 필요하지 않은 단순 달력 표시 기능 |

## Issues Encountered

### 1. OpenAPI 섹션 제거

**문제**:

OpenAPI Specification 섹션이 UI-only 기능과 맞지 않음.

**원인**:

템플릿이 기본적으로 API 문서를 포함하도록 되어 있었음.

**해결**:

OpenAPI Specification 섹션을 삭제.

**결과**:

요구사항 문서가 UI 기능에 맞게 정리됨.

## Learnings

### 기본 기능 범위 정리 (2026-02-14)

월간 달력 표시, 월 이동, 오늘 이동에 범위를 제한했다.

### API 필요 없음 명시 (2026-02-14)

spec에서 API 없이 클라이언트만으로 구현하는 기능임을 명시했다.

### 계획 단계 정리 (2026-02-14)

Outside-in 순서와 TDD 흐름을 plan에 반영하고, tasks/progress 작성 단계에
진입했다.

### 초기 설정 완료 (2026-02-14)

Vite + React + TypeScript + Tailwind + Vitest/RTL + Prettier를 기본 툴체인으로
설정했다. Node 버전은 `.nvmrc`로 고정했다.

### 모호한 체크박스 제거 (2026-02-14)

범위가 불명확한 "초기 컴포넌트/유틸 파일 구조 확정" 항목을 제거하고 명확한
체크박스만 유지했다.

### UI 규칙 확정 (2026-02-14)

주 시작 요일은 일요일로 고정하고, 오늘 버튼은 상단 오른쪽에 "오늘" 라벨로
배치하기로 결정했다.

### Outside-in 첫 연결 (2026-02-14)

App에서 MonthCalendar를 먼저 연결해 사용자 진입점을 확보했다.
