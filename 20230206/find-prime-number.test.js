/**
 *
 * 1부터 number 사이에 있는 소수의 개수를 반환하는 함수를 작성하라.
 *
 * @param {} number
 * @returns result
 */

const primeNumbersCount = (number) => {

    // 0. Set Up

    let origins = [];

    [...Array(number - 1)].forEach((acc, i) => origins[i + 2] = true);

    // 1. Process

    origins.some((value, i) => {
        if (!value) {
            return false;
        }

        if (isPrimeNumber(i, origins)) {
            for (let j = i * 2; j <= number; j += i) {
                origins[j] = false;
            }

            if (i * i > number) {
                return true;
            }
        }
    })

    return origins.filter(i => i).length;
}

const isPrimeNumber = (number, result) => {
    for (let i = 2; i < number; i += 1) {
        if (result[i]) {
            continue;
        }

        if (number % i === 0) {
            return false;
        }
    }

    return true;
}



test('1부터 number 사이의 소수의 갯수를 리턴하라', () => {
    expect(primeNumbersCount(1)).toBe(0);
    expect(primeNumbersCount(1)).toBeFalsy;

    expect(primeNumbersCount(4)).toBe(2);
    expect(primeNumbersCount(5)).toBe(3);
    expect(primeNumbersCount(10)).toBe(4);
});

test('isPrimeNumber', () => {
    expect(isPrimeNumber(1, {})).toBe(true);
    expect(isPrimeNumber(2, {})).toBe(true);
    expect(isPrimeNumber(3, {})).toBe(true);
    expect(isPrimeNumber(5, {})).toBe(true);

    expect(isPrimeNumber(4, {})).toBe(false);
});

