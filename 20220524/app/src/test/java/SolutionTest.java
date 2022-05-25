import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 배열의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때,
// k번째 있는 수를 구하기

// 1. 배열을 자른다.
// 2. 배열을 정렬한다.
// 3. 정렬한 배열의 k번째 수를 반환 한다.

public class SolutionTest {
    @Test
    void solution() {
        assertThat(solution(
                        new int[]{1, 5, 2, 6, 3, 7, 4},
                        new int[][]{{2, 5, 3}, new int[]{4, 4, 1}, new int[]{1, 7, 3}}))
                .isEqualTo(new int[]{5, 6, 3});
    }

    @Test
    void i부터_j까지_배열을_자른다() {
        assertThat(sliceArray(new int[]{1, 5, 2, 6, 3, 7, 4}, 2, 5))
                .isEqualTo(new int[]{5, 2, 6, 3});
        assertThat(sliceArray(new int[]{1, 5, 2, 6, 3, 7, 4}, 1, 6))
                .isEqualTo(new int[]{1, 5, 2, 6, 3, 7});
    }

    // 구현
    private int[] solution(int[] array, int[][] commands) {
        List<Integer> result = new ArrayList<>();

        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];

            int[] sliced = sliceArray(array, i - 1, j);

            Arrays.sort(sliced);

            result.add(sliced[k - 1]);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();

    }

    private int[] sliceArray(int[] array, int start, int end) {
        // array index 시작 위치: commands[0][0] - 1
        // array index 종료 위치: commands[0][1]
        return Arrays.copyOfRange(array, start, end);
    }

//    @Test
//    void 자른_배열을_정렬하여_k번째_수를_리턴한다() {
//        assertThat(kthNumber(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}}))
//                .isEqualTo(5);

//    }


//    private int kthNumber(int[] array, int[][] commands) {
//        // 2. 배열을 정렬한다.
//
//        IntStream newArray = IntStream.of(sliceArray(array, commands))
//                .sorted();
//
//        // 3. 정렬한 배열의 k번째 수를 반환 한다.
//        return newArray.map(i -> commands[0][2] - 1).sum();
//    }
}