# Prompt Guide

## 1. 프로젝트 시작 규칙 선언

```text
File-based Planning Workflow에 따라서 달력 웹 앱을 만들 거야.

README.md -> 사람 검토 -> spec.md -> 사람 검토 -> plan.md -> 사람 검토 ->
tasks.md, progress.md, findings.md 순서로 진행하는 거지.

앱 제목이 들어간 README.md와 방금 설명을 반영한 AGENTS.md만 만들어.
```

프로젝트의 기본 워크플로우를 확정한다.

## 2. 커뮤니케이션 언어 확정

```text
한국어로 대화하자.
```

협업 언어를 고정해 오해를 줄인다.

## 3. 포맷 규칙 확정

```text
npx prettier로 마크다운 정리할 거야.
마크다운 가로 제약은 80컬럼이고 코드와 테이블은 제외.
```

문서 포맷 기준을 통일한다. prettier 실행을 빼먹지 않도록 명시한다.

## 4. AGENTS 문서 역할 확정

```text
사람 검토는 파일에 대한 거라 진행 순서에서 각 파일 옆에 붙여.
spec.md는 요구사항 중심이고, plan.md는 기술/구현 계획이야.
tasks.md는 체크리스트고 progress.md는 tasks.md와 맞춰 진행 상황을 기록해.
findings.md는 사실상 MEMORY에 가까워.
```

문서별 역할을 고정해서 이후 충돌을 줄인다.

## 5. AGENTS 작업 규칙 확정

```text
문서 업데이트, 검토, 동기화, 검토, prettier를 매번 자주 실행해.
Outside-in으로 개발하고 TDD 무조건 적용해.
체크박스 1개 완료마다 문서 업데이트 후 검증 및 개선 3회하고 커밋해.
```

작업 원칙과 실행 리듬을 고정한다.

## 6. 기술 스택 확정

```text
기술 스택은 TypeScript, React, Tailwind, Vite야.
pnpm 쓰고 Node.js는 .nvmrc로 고정.
단위 테스트는 Vitest + React Testing Library, E2E 테스트는 Playwright.
```

구현과 테스트 방향을 통일한다. 도구 추가/변경은 AGENTS.md에 즉시 반영해야 한다.

## 7. 기능 문서 세트 시작

```text
달력 보여주고 이동하는 기본 기능부터 만들 거야.

specs/적당한 이름 폴더에 해당 기능을 위한 스펙 파일 만들자.
아래 저장소를 보고 6개 파일 모두 그대로 복사하는 걸로 시작하자.

https://github.com/ahastudio/file-based-planning-workflow

파일 복사 끝났으면 specs/적당한 이름/README.md 파일에 기능 설명 써줘.
```

기능 단위 문서 구조와 작성 시작점을 고정한다.

## 8. spec.md 작성

```text
spec.md 작성해. 오늘 달 표시, 이전/다음 이동, 오늘 버튼 포함.
요구사항과 시나리오 중심으로.
```

요구사항과 사용자 시나리오를 중심으로 기능을 정의한다. 기술 구현 세부사항은
제외한다.

## 9. plan.md 작성

```text
plan.md 작성해. Outside-in 순서로 App부터.
TDD/ATDD 관점도 반영하고 레이어 구분 말고 구현 순서 중심으로 정리해.
```

Outside-in과 TDD 흐름에 맞춰 구현 단계를 정리한다.

## 10. tasks.md, progress.md, findings.md 착수

```text
tasks.md, progress.md, findings.md 작성 단계로 가자.
TDD 사이클(Red-Green-Refactoring)을 아주 작게 자주 돌리게 해.
```

체크리스트와 진행 기록을 시작하고 리팩터링 리듬을 고정한다.

## 11. 수동 테스트 전략 위치 확정

```text
수동 테스트 전략은 README.md의 How it works 아래에 추가해.
가능하면 수동 테스트를 E2E로 자동화해서 재현 가능하게 유지해.
```

사람이 수행할 테스트 전략을 문서의 눈에 띄는 위치에 배치한다.

## 12. 잠재 이슈 검토 및 질문

```text
잠재적인 문제가 있는지 검토해줘.
```

리스크/모호점을 먼저 점검하고, 필요한 결정을 질문으로 끌어낸다.

## 13. UI 결정 확정

```text
달력의 주 시작 요일은 일요일로 고정.
오늘 버튼 라벨은 "오늘"이고 상단 오른쪽 배치.
```

모호한 UI 결정을 확정해 테스트와 구현 기준을 고정한다.

## 14. 진행 문서 운용 규칙 추가

```text
progress.md의 Test Results는 최신 상태로 갱신하고 append하지 않아.
Error Log는 append로 계속 누적하고, Error Log의 Attempt는 숫자만 써줘.
Test Results의 Input에는 날짜를 넣지 말고, 입력이 없으면 "-"로 둬.
Test Results의 Test 컬럼은 기능/시나리오 이름으로 적어.
테스트 실행 명령은 Error Log의 Attempt에만 기록해.
```

문서 표의 의미를 고정해 혼선을 줄인다.

## 15. 구현 진행 방식 명시

```text
progress.md 파일 보고 작업 시작해.
계속 진행하면서 문서 업데이트와 커밋을 반복해.
```

진행 기준을 문서 기반으로 고정해 누락 없이 추적한다.
