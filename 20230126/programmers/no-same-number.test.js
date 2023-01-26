const solution = (numbers) => numbers.filter((x, y) => x !== numbers[y - 1]);

describe('연속적으로 나타나는 숫자를 제거하고', () => {
  it('배열을 리턴한다', () => {
    expect(solution([1, 1, 3, 3, 0, 1, 1])).toEqual([1, 3, 0, 1]);
    expect(solution([4, 4, 4, 3, 3])).toEqual([4, 3]);
  });
});

describe('반복되는 수가 없을 때', () => {
  it('원본 배열을 리턴한다', () => {
    expect(solution([1, 2, 3, 4])).toEqual([1, 2, 3, 4]);
  });
});

describe('빈 배열이 주어졌을 때', () => {
  it('undefined를 리턴한다', () => {
    expect(solution([])).toBe(undefined);
  });
});
