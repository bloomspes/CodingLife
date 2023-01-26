const hamburger = [1, 2, 3, 1];

const packed = (ingredients, start) => {
  for (let i = start; i < ingredients.length - 3; i++) {
    if (ingredients[i] === hamburger[0]
            && ingredients[i + 1] === hamburger[1]
            && ingredients[i + 2] === hamburger[2]
            && ingredients[i + 3] === hamburger[3]
    ) {
      return i;
    }
  }
};

const solution = (ingredients) => {
  let count = 0;
  let start = 0;

  while (true) {
    const index = packed(ingredients, start);

    if (index === undefined) {
      return count;
    }

    count += 1;

    ingredients.splice(index, 4);
    start = index - 3;
  }
};

describe('빵-야채-고기-빵 순서로 만들어진', () => {
  it('햄버거의 수를 리턴하라', () => {
    expect(solution([1, 2, 3, 1])).toBe(1);
    expect(solution([1, 2, 3, 1, 1, 2, 3, 1])).toBe(2);

    expect(solution([1, 2, 3, 1, 2, 3])).toBe(1);
    expect(solution([1, 2, 3, 1, 1])).toBe(1);
  });
});

describe('재료의 순서가 불규칙적으로 주어질 때', () => {
  it('햄버거의 수를 리턴하라', () => {
    expect(solution([2, 1, 1, 2, 3, 1, 2, 3, 1])).toBe(2);
    expect(solution([1, 3, 2, 1, 2, 1, 3, 1, 2])).toBe(0);
  });
});

describe('재료가 없을 때', () => {
  it('햄버거의 수를 리턴하라', () => {
    expect(solution([])).toBe(0);
  });
});
