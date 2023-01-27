/**
 * TODO: 문자열의 길이가 4 혹은 6이고, 숫자로만 구성 되어있는지 확인해주는 함수를 작성하라.
 * 문자열은 길이 1이상, 길이 8이하.
 * 문자열은 영문 알파벳 대소문자 또는 0-9 까지의 숫자로 이루어져 있다.
 */

const handling = (string) => {
  if (string.length === 4 ||
      string.length === 6) {
        // e 붙으면 지수 되는 경우 처리
        return string.split("").every(char => !isNaN(char));
  }
  return false;
};

// test('길이가 4 또는 6이면 true를 리턴하라', () => {
//   expect(handling('a234')).toBeTruthy;
//   expect(handling('123456')).toBeTruthy;
// });

describe('길이가 4 또는 6 이면서', () => {
  it('숫자로만 구성되어 있으면 true를 리턴하라', () => {
    expect(handling("1234")).toBeTruthy;
    expect(handling('123456')).toBeTruthy;
  });
});

describe('길이가 4 또는 6 이면서', () => {
  it('문자열이 들어간 경우 false를 리턴하라', () => {
    expect(handling('abcdef')).toBeFalsy;
    expect(handling('a12345')).toBeFalsy;
  });
});

describe('길이가 6 초과 한 경우', () => {
  it('false를 리턴하라', () => {
    expect(handling('1234567')).toBeFalsy;
  });
});

describe('모든 요소가 숫자인 경우', () => {
  it('true를 리턴하라', () => {
    expect(handling('12345e')).toBeFalsy;
  });
});
