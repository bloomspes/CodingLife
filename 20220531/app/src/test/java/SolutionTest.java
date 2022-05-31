import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    private int solution(int[] ponkemons) {
        HashSet<Integer> type = new HashSet<>();
        int amounts = ponkemons.length / 2;

        IntStream.of(ponkemons).forEach(monster -> type.add(monster));

        if (type.size() > amounts) {
            return amounts;
        }
        else {
            return type.size();
        }
    }

    @Test
    void solutionTest() {
        assertThat(solution(new int[]{3, 1, 2, 3})).isEqualTo(2);
        assertThat(solution(new int[]{3, 3, 3, 2, 2, 4})).isEqualTo(3);
        assertThat(solution(new int[]{3, 3, 3, 2, 2, 2})).isEqualTo(2);
    }

}