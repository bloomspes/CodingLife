import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

// 문자의 갯수가 반복되는 경우, 반복되는 갯수를 숫자로 표기하여 문자열을 리턴한다.
// 리턴한 문자열 중에 가장 짧은 문자열을 구하는게 목표.


// 1. 문자열을 1개 단위로 자르는게 가장 짧은 경우
// 1-1. 중복된 문자열을 숫자로 치환한다.

// 2. 문자열을 2개 이상의 단위로 자르는게 가장 짧은 경우
// 3. 중복된 문자열을 숫자로 치환한다.
// 4. 문자열이 최대로 중복되는 경우는 전체 문자열의 1/2가 되지 않는다.


class SolutionTest {

    @Test
    void 문자열을_슬라이싱_한다() {
        assertThat(sliced("aabb")).isEqualTo(new String[]{"a", "a", "b", "b"});
        assertThat(sliced("aabbaccc")).isEqualTo(new String[]{"a", "a", "b", "b", "a", "c", "c", "c"});
    }

    @Test
    void 중복된_문자열을_숫자로_치환한다() {
        assertThat(replaceToNumbers("aabb")).isEqualTo("2a2b");
    }

    @Test
    void 문자열을_비교하여_중복된_갯수를_찾는다() {
        assertThat(findDuplicated("aabb")).isEqualTo(new int[]{2, 2});
    }

    private int[] findDuplicated(String letters) {
        for (int i = 0; i < letters.length(); i++) {

        }
        return new int[]{2, 2};
    }

    private String replaceToNumbers(String letters) {
        List<String> sliced = List.of(letters.split(""));

        Set<String> duplicated = new HashSet<>(sliced);
        for (String letter : duplicated) {
            Collections.frequency(sliced, letter);
        }

        System.out.println(duplicated);
        return "2a2b";
    }

    private String[] sliced(String letters) {
        return letters.split("");
    }

}