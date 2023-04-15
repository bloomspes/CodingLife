const {
    score, nextBalls1, nextBalls2, nextBalls3, nextBalls4, sum,
} = require('./game');

test('sample', () => {
    expect(score([
        [10],
        [10],
        [10],
        [7, 2],
        [8, 2],
        [0, 9],
        [10],
        [7, 3],
        [9, 0],
        [10, 10, 8]
    ])).toBe(30 + 27 + 19 + 9 + 10 + 9 + 20 + 19 + 9 + 28);

    expect(score([
        [10],
        [10],
        [10],
        [7, 2],
        [8, 2],
        [0, 9],
        [10],
        [7, 3],
        [9, 0],
        [10, 0, 0]
    ])).toBe(30 + 27 + 19 + 9 + 10 + 9 + 20 + 19 + 9 + 10);
});

test('normal case', () => {
    expect(score([[2, 5]])).toBe(7);

    expect(score([
        [2, 5],
        [3, 6]
    ])).toBe(16);

    expect(score([
        [2, 5],
        [3, 6],
        [1, 4]
    ])).toBe(21);

});

test('spare', () => {
    expect(score([
        [3, 7],
        [5, 2]
    ])).toBe(15 + 7);

    expect(score([
        [3, 7],
        [5, 5],
        [1, 3]
    ])).toBe(15 + 11 + 4);
});

test('strike', () => {
    expect(score([
        [10],
        [5, 2]
    ])).toBe(17 + 7);

    expect(score([
        [10],
        [10, 0, 0]
    ])).toBe(20 + 10);

    expect(score([
        [10],
        [10, 10, 8]
    ])).toBe(30 + 28);
});

test('sum', () => {
    expect(sum([2, 5])).toBe(7);
    expect(sum([2, 5, 7])).toBe(14);
    expect(sum([])).toBe(0);
});

test('nextBalls', () => {
    [nextBalls1, nextBalls2, nextBalls3, nextBalls4].forEach((nextBalls) => {
        expect(nextBalls([
            [10],
            [10, 1, 2]
        ], 0)).toEqual([10, 1]);

        expect(nextBalls([
            [10],
            [10, 1, 2]
        ], 1)).toEqual([1, 2]);

        expect(nextBalls([
            [10],
            [10],
            [3, 4],
            [0, 0]
        ], 0)).toEqual([10, 3]);

        expect(nextBalls([
            [10],
            [10],
            [3, 4],
            [0, 0]
        ], 1)).toEqual([3, 4]);

        expect(nextBalls([
            [10],
            [5, 5],
            [3, 4],
            [0, 0]
        ], 1)).toEqual([3]);

        expect(nextBalls([
            [10],
            [5, 5, 4],
        ], 1)).toEqual([4]);
    });
});
