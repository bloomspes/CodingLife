function solution(id_list, report, k) {

    const answer = new Array(id_list.length).fill(0);

    const list = {};

    id_list.map((user) => {
        list[user] = [];
    });

    report.map((user) => {
        const [id, report_id] = user.split(' ');

        if(!list[report_id].includes(id)) {
            list[report_id].push(id);
        };
    });

    for (const key in list) {
        if (list[key].length >= k) {
            list[key].map((user) => {
                answer[id_list.indexOf(user)] += 1;
            });
        };
    };

    return answer;
}




describe("정지 메일을 받는다", () => {
    it("2번 신고를 받는 경우", () => {
        const id_list = ["muzi", "frodo", "apeach", "neo"];
        
        const report = [
            "muzi frodo",
            "apeach frodo",
            "frodo neo",
            "muzi neo",
            "apeach muzi"
        ];

        const k = 2;

        expect(solution(id_list, report, k)).toEqual([2, 1, 1, 0]);
    });
});

describe("정지 메일을 받는다", () => {
    it("3번 신고를 받는 경우", () => {
        const id_list = ["con", "ryan"];

        const report = [
            "ryan con", 
            "ryan con", 
            "ryan con", 
            "ryan con"
        ];

        const k = 3;

        expect(solution(id_list, report, k)).toEqual([0, 0]);
    });
});