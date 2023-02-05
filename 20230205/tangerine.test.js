const differentSize = tangerines => tangerines.reduce((acc, cur) => {
    acc[cur] = (acc[cur] || 0) + 1;
    return acc;
}, [undefined]);

const run = (k, tangerines) => {
    const counts = differentSize(tangerines);

    return new Set(
        tangerines.sort((a, b) => {
            if (counts[b] < counts[a]) {
                return -1;
            }
            if (counts[b] > counts[a]) {
                return 1;
            }

            return b - a;
        }).slice(0, k),
    ).size;
};

test('run', () => {
    expect(run(6, [1, 3, 2, 5, 4, 5, 2, 3])).toBe(3);
})

test('counts', () => {
    expect(differentSize([1, 3, 2, 5, 4, 5, 2, 3])).toEqual([
        undefined,
        1,
        2,
        2,
        1,
        2
    ]);
})