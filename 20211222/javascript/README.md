# Jest.js로 Javascript TDD 입문하기

- 출처 : https://github.com/ahastudio/til/blob/main/jest/20201204-simple-tdd-example.md

[Getting Started](https://jestjs.io/docs/getting-started)

## 프로젝트 셋팅

  ```  
  npm init -y

  npm i -D eslint jest @types/jest

  npx eslint --init 
  ```

  `.eslintrc.js` 파일 세팅하기  

  ```
  env: {
      es2021: true,
      node: true,
      jest: true
  },

  'rules': {
    'indent': ['error', 2],
    'linebreak-style': ['error', 'unix'],
    'quotes': ['error', 'single'],
    'semi': ['error', 'always'],
    'no-trailing-spaces': 'error',
    'curly': 'error',
    'brace-style': 'error',
    'no-multi-spaces': 'error',
    'space-infix-ops': 'error',
    'space-unary-ops': 'error',
    'no-whitespace-before-property': 'error',
    'func-call-spacing': 'error',
    'space-before-blocks': 'error',
    'keyword-spacing': ['error', { 'before': true, 'after': true }],
    'comma-spacing': ['error', { 'before': false, 'after': true }],
    'comma-style': ['error', 'last'],
    'comma-dangle': ['error', 'always-multiline'],
    'space-in-parens': ['error', 'never'],
    'block-spacing': 'error',
    'array-bracket-spacing': ['error', 'never'],
    'object-curly-spacing': ['error', 'always'],
    'key-spacing': ['error', { 'mode': 'strict' }],
    'arrow-spacing': ['error', { 'before': true, 'after': true }],
  },
  ```

  ## 문제 이해하기

  "두 수를 뽑아서 더하기" 
  - 입력: 숫자 목록
  - 출력: 입력된 숫자 목록에서 두 개를 뽑아서 더한 값을 모아 중복을 제거하고 정렬한 숫자 목록 출력하기

