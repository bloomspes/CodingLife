# Setup Project for Coding tests

```bash

npm init -y

npm i -D eslint jest @types/jest

npx eslint --init 
# Use a airbnb style (Choose)
```

`.eslintrc.js` 파일을 열어서 다음과 같이 수정한다.

```json
  env: {
    es2021: true,
    node: true,
    jest: true,
  },
```

```json
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

ESlint로 고쳐야 할 부분을 찾을 수 있다.

```bash
npx eslint .
```

다음 명령을 실행하면 코드를 검사하고 자동으로 픽스해준다.

```bash
npx eslint --fix .
```

`package.json`의 `scripts` 항목에 `lint`를 추가하면 이 작업을 편하게 할 수 있다.

```json
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "lint": "eslint --fix .  # <- 여기에 lint 명령을 추가"
  },
```

그러면 아래와 같이 ESlint를 실행할 수 있다.
```bash
npm run lint
```

파일이 수정될 때마다 자동으로 테스트가 실행되게 하려면  `--watchAll` 옵션을 붙이면 된다.

```bash
npx jest --watchAll
```

마지막으로 `.eslintrc.js` 과 `package.json` 파일을 수정해준다.

```bash
  'env': {
    'es6': true,
    'node': true,
    // Jest 사용
    'jest': true,
  },
```

```bash
  "scripts": {
    "test": "jest  # <- 기존의 에러 종료를 Jest 실행으로 변경",
    "lint": "eslint --fix ."
  },
```