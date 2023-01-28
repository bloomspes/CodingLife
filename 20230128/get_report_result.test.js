const solution = (list, report, k) => {
    let users = extract(list, report);

    let result = new Array(list.length).fill(0);

    for(const key in users) {
        if (users[key].length >= k) {
            users[key].map((user) => {
                result[list.indexOf(user)] += 1;
            });
        };
    };

    return result;
};

const extract = (list, report) => {
    let users = {};

    list.map((user) => {
        users[user] = [];
    });

    report.map((user) => {
        const [id, report_id] = user.split(' ');

        if (!users[report_id].includes(id)) {
            users[report_id].push(id);
        }
    });

    return users;
};


describe('2번 미만 신고당한 경우에', () => {
    it("처리 결과 횟수 0을 리턴한다", () => {
          expect(solution(
            [
                "con",
                "ryan"
            ],
            [
                "ryan con",
                "ryan con",
                "ryan con",
                "ryan con"
            ],
            3
          )).toEqual([0, 0]);
    });
});

describe('2번 이상 신고당한 경우에', () => {
    it('처리 결과 횟수를 리턴한다', ()  => {
        expect(solution(
            [
                "muzi",
                "frodo",
                "apeach",
                "neo"
            ],
            [
                "muzi frodo",
                "apeach frodo",
                "frodo neo",
                "muzi neo",
                "apeach muzi"
            ],
            2
        )).toEqual([2, 1, 1, 0]);
    });
});

describe('유저와 신고한 유저를 분류해서', () => {
    it('처리 결과를 받을 유저를 리턴하라', () => {
        expect(extract(
            ["muzi", "frodo", "apeach", "neo"],
            [
                "muzi frodo",
                "apeach frodo",
                "frodo neo",
                "muzi neo",
                "apeach muzi"
            ]
        )).toEqual(
            {
                "apeach": [],
                "frodo": ["muzi", "apeach"],
                "muzi": ["apeach"],
                "neo": ["frodo", "muzi"]
            });
    });
});

