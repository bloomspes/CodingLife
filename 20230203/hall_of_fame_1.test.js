const solution = (k, score) => {
    const ranker = []

    return updateScores(score, ranker, k);
}

const updateScores = (score, ranker, k) => {

    return score.reduce((acc, cur) => {
        if (ranker.length < k) {
            ranker.push(cur);

            ranker.sort((x, y) => x - y);
        }

        else {
            ranker.push(cur);

            ranker.sort((x, y) => x - y);

            ranker.shift();
        }

        acc.push(ranker[0]);

        return acc;
    }, []);
}


test('solution', () => {
    expect(solution(3, [10, 100, 20, 150, 1, 100, 200]))
    .toEqual([10, 10, 10, 20, 20, 100, 100]);
})

test('updateScores', () => {
    expect(updateScores([10, 100, 20, 150], [10, 10, 10], 3))
    .toEqual([10, 10, 10, 20]);

    expect(updateScores([10, 100, 20, 150, 1], [10, 10, 10, 20, 20], 5))
    .toEqual([10, 10, 10, 20, 20]);
})