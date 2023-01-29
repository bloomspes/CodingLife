const solution = clothes => {
    const clothesMap = clothes.reduce((acc, [name, type]) => {
      const value = acc.get(type);
      if (!value) {
        acc.set(type, [name]);
        return acc;
      }

      acc.set(type, [...value, name]);

      return acc;
    }, new Map());

    return 1;
  };

  test('run', () => {
    expect(
      solution(
        [['yellowhat', 'headgear']]
      ),
    ).toBe(1);

    expect(
      solution(
        [['yellowhat', 'headgear'], ['redhat', 'headgear']]
      ),
    ).toBe(2);
  });