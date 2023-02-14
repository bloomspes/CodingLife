// 3개의 배열은 연속된 수의 패턴이다.
// answers와 pattern의 수가 가장 많이 겹치는 것 찾기.

const patterns = [
    [1, 2, 3, 4, 5],
    [2, 1, 2, 3, 2, 4, 2, 5],
    [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
]

const correct = (pattern, answer, index) =>
    answer === pattern[index % pattern.length];

const first = x => x[0];

const solution = (answers) => {
    const scores = patterns.map((pattern, i) =>
        answers.reduce((acc, cur, index) =>
        correct(pattern, cur, index) ? acc + 1 : acc, 0));

    const highestScores = Math.max(...scores);

    return scores.map((score, index) => [index + 1, score])
    .filter(([_, score]) => score === highestScores).map(first);
};


test('solution', () => {
    expect(solution([1, 2, 3, 4, 5])).toEqual([1]);
    expect(solution([1, 3, 2, 4, 2])).toEqual([1, 2, 3]);
})
