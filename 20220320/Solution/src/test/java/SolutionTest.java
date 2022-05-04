import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


// 맥스웰님 접근

// 1. 먼저, 0부터 9까지의 모든 수가 들어있는 배열을 만든다.
// 2. 1로 생성된 배열에서, 매개변수로 받은 배열내 요소들을 찾아서 뺀다.
// 3. 2에서 최종적으로 생긴 배열의 모든 요소를 찾아서 더한다.

// 내가 생각하는 맥스웰님 풀이 장점: 각각의 배열의 요소를 찾을 수 있어서
// 문제가 변형되는 경우, 다양한 풀이를 생각할 수 있을 것 같다.

// 나의 접근
// 결국 구하려는 것은 배열의 합.
// (0부터 9까지의 합) - (sum(numbers()))


class SolutionTest {
    @Test
    void NumberTest() {
        assertThat(sumNotIncludedArrayNumbers(new int[]{1}, 45)).isEqualTo(45 - 1);
        assertThat(sumNotIncludedArrayNumbers(new int[]{1, 2}, 45)).isEqualTo(45 -1 -2);
        assertThat(sumNotIncludedArrayNumbers(new int[]{1, 2, 3}, 45)).isEqualTo(45 -1 -2 -3);

        assertThat(sumNotIncludedArrayNumbers(new int[]{1, 2, 3, 4, 6, 7, 8, 0}, 45)).isEqualTo(14);
        assertThat(sumNotIncludedArrayNumbers(new int[]{5, 8, 4, 0, 6, 7, 9}, 45)).isEqualTo(6);

        assertThat(NotAllowedNegativeNumbers(new int[]{-1})).isFalse();
    }

    // 구현
    private Integer sumNotIncludedArrayNumbers(int[] numbers, int totalValue) {
        return totalValue - sumNumbers(numbers);
    }

    private Integer sumNumbers(int[] numbers) {
        return IntStream.of(numbers).sum();
    }

    // 음수가 있을 때, false 반환
    private Boolean NotAllowedNegativeNumbers(int[] numbers) {
        for(int num : numbers){
            if(num < 0){
                return false;
            }
        }
        return true;
    }
}