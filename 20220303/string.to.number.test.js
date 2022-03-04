// 1. digit : word hashmap 구조 만들기
// 2. 문자열 s 데이터 tokenize 해서 1의 데이터에 매칭시킬 것
// 3. 매칭 된 키-값을 return 한다.

function solution(s) {

    let answer = s;

    const words = [
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
    ]

    words.forEach((word, index) => answer = answer.replace(new RegExp(word, 'g'), index));

    return Number(answer);
}

describe("숫자 123을 리턴 한다", () => {
    it("문자열 123을 입력하면", () => {

        expect(solution("123")).toEqual(123);
    });
});


describe("숫자 1478을 리턴 한다", () => {
    it("one4seveneight을 입력하면", () => {

        expect(solution("one4seveneight")).toEqual(1478);
    });
});


describe("숫자 234567을 리턴 한다", () => {
    it("문자열 23four5six7을 입력하면", () => {

        expect(solution("23four5six7")).toEqual(234567);
    });
});

describe("숫자 234567을 리턴 한다", () => {
    it("문자열 2three45sixseven을 입력하면", () => {

        expect(solution("2three45sixseven")).toEqual(234567);
    });
});

