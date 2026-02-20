# 지침

이 프로젝트는 **File-Based Planning Workflow**를 따릅니다.

참고: <https://github.com/ahastudio/file-based-planning-workflow>

---

## 작업 흐름

문서는 다음 순서로 작성하고, 각 파일을 사람이 검토한 후 다음 단계로 진행합니다.

1. README (사람 검토)
2. spec (사람 검토)
3. plan (사람 검토)
4. tasks, progress, findings (작업하면서 계속 갱신)

### 문서 체계

- **README.md** — 기능 개요, 배경, 목표를 간단히 서술
- **spec.md** — 요구사항 중심 (유저 스토리, 인수 시나리오, 기능 요구사항). 기술 내용 제외
- **plan.md** — 기술 및 구현 계획 (아키텍처, 구현 단계, 검증 방법)
- **tasks.md** — 구현 작업 체크리스트
- **progress.md** — tasks와 맞추며 진행 상황을 지속적으로 기록
- **findings.md** — 발견한 사실, 결정, 배운 점을 즉시 기록. 사실상 프로젝트 메모리

---

## 규칙

### 반복 사이클

의미 있는 변경이 생길 때마다 다음 사이클을 자주 반복한다.

1. 문서 업데이트 (findings, progress 등 관련 파일 즉시 반영)
2. 문서 간 내용 동기화 확인
3. `npm run format` 실행
4. 커밋

### 문서 관리

- 각 단계 문서를 작성한 뒤 **반드시 사람 검토를 기다린다.**
- 사람이 승인하기 전에 다음 단계 문서를 작성하지 않는다.
- `findings.md`는 예외로, 배운 점이 생기면 **즉시** 기록한다.
- 사고 과정을 끊임없이 기록한다.

### 커밋

- 커밋 메시지는 변경 내용을 명확히 설명한다.

### 개발 방식

- 기능을 시작할 때 `specs/` 아래에 기능 이름을 반영한 적절한 kebab-case 폴더를 만들고, 템플릿 6개 파일을 복사한 뒤 README.md부터 작성한다.
- 구현은 `plan.md`가 승인된 이후에 시작한다.
- **Outside-in** 순서로 개발한다. (UI → Application → Domain)
- **TDD**를 무조건 적용한다. (Red → Green → Refactor)
- 구현 중 발견한 사항은 `findings.md`에 즉시 기록한다.
- 진행 상황은 `progress.md`에 세션 단위로 기록한다.
