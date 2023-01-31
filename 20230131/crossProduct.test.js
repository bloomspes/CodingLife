// Only use reduce.
const run = (a, b) => a.reduce((acc, cur, index) => acc + (cur * b[index]), 0);

// Use map, reduce.
const run2 = (a, b) => a.map((cur, i) => cur * b[i]).reduce((a, b) => a + b);

test('내적 계산을 리턴하라', () => {
    expect(run([1, 2, 3, 4], [-3, -1, 0, 2])).toBe(3);
    expect(run([-1, 0, 1], [1, 0, -1])).toBe(-2);
});

test('run2', () => {
    expect(run2([1, 2, 3, 4], [-3, -1, 0, 2])).toBe(3);
    expect(run2([-1, 0, 1], [1, 0, -1])).toBe(-2);
});