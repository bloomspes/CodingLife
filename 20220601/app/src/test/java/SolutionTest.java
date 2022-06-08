import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import java.util.Collections;
import java.util.Comparator;


import static org.assertj.core.api.Assertions.assertThat;

// 실패율이 높은 스테이지 부터 내림차순으로
// 스테이지의 번호가 담겨는 배열을 반환한다.

// 실패율 = (스테이지에 도달한 플레이어 중 클리어 못한 사람의 수) / 스테이지에 도달한 플레이어 수

// N = 전체 스테이지의 갯수
// stages = 전체 참가자들이 머무르고 있는 현재 스테이지의 집합


// 스테이지가 바뀔 때마다 실패율을 계산 할때
// 해당 스테이지에 머무르고 있는 참가자들의 수를 구해야 한다.

class SolutionTest {
    @Test
    void 실패율을_계산하라() {
        assertThat(solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}))
                .isEqualTo(new int[]{3, 4, 2, 1, 5});
    }

    private int[] solution(int stages, int[] level) {

        int[] user = new int[stages + 2];
        int[] users = new int[stages + 1];

        for (int i = 0; i < level.length; i++) {
            user[level[i]] += user[level[i]] + 1;
        }

        // 누적 유저 구하기
        users[stages] = user[stages] + user[stages + 1];

        // 실패율을 담자
        ArrayList<Double> rates = new ArrayList<>();

        for(int i = 1; i < stages + 1; i++) {
            if(users[i] == 0) {
                rates.add(i, (double) 0);
            }

            double rate = (double) user[i] / user[i];
            rates.add(i, rate);
        }

        // 내림차순 정렬
        Collections.sort(rates, (Comparator.comparingDouble(present -> present)));
        int[] answer = rates.stream().mapToInt(value -> Integer.intValue(value)).toArray();
        return answer;
    }

//    private int fail(int stages, int[] level) {
//        for (int i = 0; i < stages; i++) {
//            if (level[i] == i + 1) {
//                return (int) (Arrays.stream(level).count() / (stages - i));
//            }
//        }
//
//    }


}