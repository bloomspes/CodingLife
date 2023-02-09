/**
 * 행렬 first, second가 주어졌을 때 두 행렬의 곱연산을 한 행렬을 반환하라.
 *
 * @param {Array} first
 * @param {Array} second
 * @returns {{}}
 */

const run = (first, second) => {

    return first.map((row) =>
        // 행렬 곱셈 연산의 경우,  인덱스 0번의 엘리먼트를 순회하면서 연산 하기 때문에
        // 순회하는 연산은 map으로, 연산의 합은 reduce로 계산한다.
        second[0].map((_, i) =>
            row.reduce((acc, cur, j) => acc + cur * second[j][i], 0)))
}

// 사실 for 문이 더 효율이 좋음.

const run2 = (first, second) => {
    let result = Array();

    for (let i = 0; i < first[0].length; i += 1) {
        result[i] = [];

        for (let j = 0; j < second[i].length; j += 1) {
            for (let k = 0; k < first[0].length; k += 1) {
                result[i][j] = first[i][k] * second[k][j];
            }
        }
    }

    return result;
}




test('run', () => {
    expect(run(
        [
            [1, 4],
            [3, 2],
            [4, 1]
        ],
        [
            [3, 3],
            [3, 3]
        ]))
    .toEqual(
        [
            [15, 15],
            [15, 15],
            [15, 15]
        ]);

    expect(run(
        [
            [2, 3, 2],
            [4, 2, 4],
            [3, 1, 4]
        ],
        [
            [5, 4, 3],
            [2, 4, 1],
            [3, 1, 1]
        ]))
    .toEqual(
        [
            [22, 22, 11],
            [36, 28, 18],
            [29, 20, 14]
        ]);
});

test('run2', () => {
    expect(run(
        [
            [1, 4],
            [3, 2],
            [4, 1]
        ],
        [
            [3, 3],
            [3, 3]
        ]))
    .toEqual(
        [
            [15, 15],
            [15, 15],
            [15, 15]
        ]);

})

