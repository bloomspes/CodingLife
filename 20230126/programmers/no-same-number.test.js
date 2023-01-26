const solution = () => [4, 3];

describe('연속적으로 나타나는 숫자를 제거하고', () => {
  it('배열을 리턴한다', () => {
    expect(solution([4, 4, 4, 3, 3])).toEqual([4, 3]);
  });
});
