
const solution = (lottos, win_nums) => {
    // 0. Set Up
    const rank = [6, 6, 5, 4, 3, 2, 1];

    const { worst, zeroCount } = rankCount(lottos, win_nums);

    // 1. Process

    const best = worst + zeroCount;

    return [rank[best], rank[worst]]

}

const rankCount = (lottos, win_nums) => {
    const worst = lottos.filter(number => win_nums.includes(number)).length;

    const zeroCount = lottos.filter(number => number == 0).length;

    return { worst, zeroCount };
}

test('solution', () => {
    expect(solution([44, 1, 0, 0, 31, 25], [31, 10, 45, 1, 6, 19])).toEqual([3, 5]);
    expect(solution([0, 0, 0, 0, 0, 0], [38, 19, 20, 40, 15, 25])).toEqual([1, 6]);
});

