/**
 * x만큼 간격이 있는 n개의 숫자 (등차 수열 리턴하기)
 */

const run1 = (first, number) => {
   return Array(number).fill(first).map((first, index) => first * (index + 1));
}

const run2 = (first, number) => {
    return [...Array(number).keys()].map((index) => first * (index + 1));
}

describe('정수 x와 자연수 n을 입력 받아', () => {
    it('x부터 시작해 x씩 증가하는 숫자 n개를 리턴하라', () => {
        expect(run1(2, 5)).toEqual([2, 4, 6, 8, 10]);
        expect(run1(4, 3)).toEqual([4, 8, 12]);
        expect(run1(-4, 2)).toEqual([-4, -8]);

        expect(run2(2, 5)).toEqual([2, 4, 6, 8, 10]);
        expect(run2(4, 3)).toEqual([4, 8, 12]);
        expect(run2(-4, 2)).toEqual([-4, -8]);
    });
});
