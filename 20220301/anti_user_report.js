// TODO: 불량 이용자를 신고하고 처리 결과를 메일로 발송한다.

// 1. 각 유저는 한 번에 한 명의 유저를 신고할 수 있다.
// 2. 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 정지 메일을 발송한다.
// 3. 중복으로 같은 사람을 신고하는 경우, 무효 처리 한다.

function solution(id_list, report, k) {

    const answer = new Array(id_list.length);
    answer.fill(0);

    const list = {};

    id_list.map((user) => {
        list[user] = [];
    })

    report.map((user) => {
        const [id, report_id] = user.split(' ');

        if(!list[report_id].includes(id)) {
            list[report_id].push(id);
        }
    });

    for (const key in list) {
        if (list[key].length >= k) {
            list[key].map((user) => {
                answer[id_list.indexOf(user)] += 1;
            })
        }
    }

    return answer;
}