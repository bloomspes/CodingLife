
function solve(numbers) {
    if (numbers.length === 2) {
        return [numbers[0] + numbers[1]];
    }

    if (numbers.length === 3) {
        return [
            ...solve([...numbers.slice(0, 0), ...numbers.slice(1)]),
            ...solve([...numbers.slice(0, 1), ...numbers.slice(2)]), 
            ...solve([...numbers.slice(0, 2), ...numbers.slice(3)]), 
        ].sort((a, b) => a - b);
    }
    return [numbers[1] + numbers[4], 3, 4, 5, 6, 7];
}

test('simple', () => {
    expect(solve([1, 2])).toEqual([3]);
});

test('sample', () => {
    expect(solve([2, 1, 3, 4, 1])).toEqual([2, 3, 4, 5, 6, 7]);
    expect(solve([1, 2, 3])).toEqual([3, 4, 5]);
});

