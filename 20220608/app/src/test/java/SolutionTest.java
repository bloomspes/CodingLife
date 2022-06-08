
// 각 부서에 필요한 물품을 구매 비용을 지원할 때
// 최대한 몇 개의 부서에 물품을 지원할 수 있는가?

// 문제에 숨겨진 조건
// costs[i].length 만큼 정확하게 비용을 지원해야 한다.
// Arrays.stream(costs).sum() 값이 budget과 같은 경우
// costs.length를 리턴하면 된다.
// 왜냐면 모든 부서에 지원이 가능하니까.

// 이로부터 알 수 있는 사실은, budget의 값보다 같거나 작을때의 가짓 수를 리턴하면 된다.

// 처음부터 들어있는 금액부터 더해가며 budget의 값을 넘기 직전의 수를 리턴하면 된다.
// 가장 많은 부서를 반환하기 떼문에 배열을 정렬시켜서 문제를 해결하는 것이 나은 듯 하다.

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void 최대로_지원_가능한_부서의_수를_리턴하라() {
        assertThat(solution(new int[]{2, 2, 3, 3}, 10)).isEqualTo(4);
        assertThat(solution(new int[]{1, 3, 2, 5, 4}, 9)).isEqualTo(3);
    }

    private int solution(int[] costs, int budget) {
        int answer = 0;
        Arrays.sort(costs);

        for (int cost : costs) {
            if (budget - cost < 0) {
                break;
            }
            budget = budget - cost;
            answer++;
        }

        return answer;
    }

}