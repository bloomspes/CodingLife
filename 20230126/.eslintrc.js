module.exports = {
  env: {
    browser: true,
    es2021: true,
    jest: true,
  },
  extends: [
    'plugin:react/recommended',
    'airbnb',
  ],
  overrides: [
  ],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: [
    'react',
  ],
  rules: {
    // 공백 4칸에서 공백 2칸으로 변경.
    // https://eslint.org/docs/rules/indent
    indent: ['error', 2],

    // 줄 끝 공백 항상 제거.
    // https://eslint.org/docs/rules/no-trailing-spaces
    'no-trailing-spaces': 'error',

    // 블록 중괄호 항상 사용.
    // https://eslint.org/docs/rules/curly
    curly: 'error',

    // 중괄호 스타일 맞춤.
    // https://eslint.org/docs/rules/brace-style
    'brace-style': 'error',

    // 공백이 필요하면 하나만 들어게 한다.
    // https://eslint.org/docs/rules/no-multi-spaces
    'no-multi-spaces': 'error',

    // 연산자 앞뒤에 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/space-infix-ops
    'space-infix-ops': 'error',

    // 단항 연산자가 단어면 사이에 공백이 들어가게 하고, 기호면 공백을 제거.
    // https://eslint.org/docs/rules/space-unary-ops
    'space-unary-ops': 'error',

    // 속성 앞 공백 제거.
    // https://eslint.org/docs/rules/no-whitespace-before-property
    'no-whitespace-before-property': 'error',

    // 함수 호출을 위한 소괄호는 반드시 붙여서 쓰게 한다.
    // https://eslint.org/docs/rules/func-call-spacing
    'func-call-spacing': 'error',

    // 블록 앞에 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/space-before-blocks
    'space-before-blocks': 'error',

    // if, else 등 키워드 앞뒤에 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/keyword-spacing
    'keyword-spacing': ['error', { before: true, after: true }],

    // 쉼표 뒤에만 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/comma-spacing
    'comma-spacing': ['error', { before: false, after: true }],

    // 여러 줄로 여러 요소를 표현할 때 줄 마지막에 쉼표가 들어가게 한다.
    // https://eslint.org/docs/rules/comma-style
    'comma-style': ['error', 'last'],

    // 여러 줄로 여러 요소를 표현할 때 마지막 줄 끝에도 쉼표가 들어가게 한다.
    // https://eslint.org/docs/rules/comma-dangle
    'comma-dangle': ['error', 'always-multiline'],

    // 소괄호 안에 공백이 들어가지 않게 한다.
    // https://eslint.org/docs/rules/space-in-parens
    'space-in-parens': ['error', 'never'],

    // 블록 중괄호 안에 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/block-spacing
    'block-spacing': 'error',

    // Array 리터럴 대괄호 안에 공백이 들어가지 않게 한다.
    // https://eslint.org/docs/rules/array-bracket-spacing
    'array-bracket-spacing': ['error', 'never'],

    // Object 리터럴 중괄호 안에 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/object-curly-spacing
    'object-curly-spacing': ['error', 'always'],

    // Key-Value 구분을 위한 콜론 뒤에만 공백을 넣는다.
    // https://eslint.org/docs/rules/key-spacing
    'key-spacing': ['error', { mode: 'strict' }],

    // Arrow Function 기호 앞 뒤에 공백이 들어가게 한다.
    // https://eslint.org/docs/rules/arrow-spacing
    'arrow-spacing': ['error', { before: true, after: true }],
  },
};
