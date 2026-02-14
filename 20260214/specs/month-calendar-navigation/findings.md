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

- [File-based Planning Workflow]
  (https://github.com/ahastudio/file-based-planning-workflow)

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
