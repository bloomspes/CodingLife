const matchesWord = (letters, skip, index) => {
    const words = groupping(skip);

    return letters.split("").map((char) => words[(words.indexOf(char) + index) % words.length]).join("");
}


const groupping = (skip) => {
    const origins = new Set('abcdefghijklmnopqrstuvwxyz');

    return [...origins].filter((char) => !skip.includes(char));
}

// const matching = (letters, skip, index) => {

    // [...letters].forEach((word) => {
    //     const increaseIndex = groupping(skip).indexOf(word) + index;

    //     const answer = groupping(skip).map(increaseIndex => increaseIndex % groupping(skip).length);

    // }, []);
// }

describe('skip 문자열이 주어졌을 때', () => {
    it('문자열 집합에서 해당 문자열을 제거하고 새로운 배열을 리턴하라', () => {
        expect(groupping("abcdefghijklmnopqrstuvw")).toEqual(['x', 'y', 'z']);
        expect(groupping("abcdefghijklmnopqrstuv")).toEqual(['w', 'x', 'y', 'z']);
    });
})

describe('string, skip, index가 주어졌을 때', () => {
    it("조건에 부합하는 문자열을 리턴하라", () => {
       expect(matchesWord("aukks", "wbqd", 5)).toBe("happy");
    })
})


