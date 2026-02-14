# File-Based Planning Workflow

이 프로젝트는 File-based Planning Workflow를 따른다.

## 진행 순서

1. README 작성 (사람 검토)
2. spec 작성 (사람 검토)
3. plan 작성 (사람 검토)
4. tasks, progress, findings 작성 (만든 뒤 작업하면서 갱신)

## 규칙

- findings는 처음부터라도 배운 점이 있으면 계속 내용 추가
- spec은 기술이 아닌 요구사항 중심으로 작성
- plan은 기술, 구현에 대한 계획
- tasks는 체크리스트
- progress는 tasks와 맞춰 진행 상황을 기록
- findings는 사실상 MEMORY에 가까운 기록
- 문서 업데이트, 검토, 동기화, 검토를 매번 자주 실행하고 prettier도 항상
  자동으로 실행
- 기능 개발 시작 시 `specs/` 아래 기능 이름을 반영한 적절한 kebab-case 폴더를
  만들고 그 안에 6개 템플릿 파일을 그대로 복사한 뒤 해당 폴더의 `README.md`부터
  작성 시작
- 커밋도 자주 수행
- 사고 과정을 끊임없이 기록
