function solution(s) {

    let answer = s;

    const words = {
        0: "zero",
        1: "one",
        2: "two",
        3: "three",
        4: "four",
        5: "five",
        6: "six",
        7: "seven",
        8: "eight",
        9: "nine"
    }
        

    words.forEach((word, index) => answer = answer.replace(new RegExp(word, 'g'), index));
    
    return Number(answer);
}