import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// * 처음 위치
// 왼손 엄지손가락 = '*', 오른손 엄지손가락 = '#'

// * 규칙
// 1, 4, 7 => 왼손 엄지손가락 사용
// 3, 6, 9 => 오른손 엄지손가락 사용
// 2, 5, 8, 0 => 두 엄지손가락의 위치에서 더 가까운 엄지손가락을 사용
// 그런데, 두 엄지손가락 위치가 서로 같으면,
// 왼손잡이 인 경우에는 왼손 엄지손가락을 사용하고, 오른손잡이 인 경우에는 오른손 엄지손가락을 사용.

// * 중간 숫자를 누를때의 규칙
// 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.

// 구해야 할 것: 눌러야 할 엄지손가락을 모아서 문자열로 리턴하자.

class SolutionTest {
    @Test
    void whichFirstButtonNumbersTest() {
        // 왼쪽 버튼
        assertThat(printFingerList(new int[]{1}, "right")).isEqualTo("L");
        assertThat(printFingerList(new int[]{4}, "right")).isEqualTo("L");
        assertThat(printFingerList(new int[]{7}, "right")).isEqualTo("L");

        // 오른쪽 버튼
        assertThat(printFingerList(new int[]{3}, "right")).isEqualTo("R");
        assertThat(printFingerList(new int[]{6}, "left")).isEqualTo("R");
        assertThat(printFingerList(new int[]{9}, "right")).isEqualTo("R");

        // 가운데 버튼
        assertThat(printFingerList(new int[]{2}, "right")).isEqualTo("R");
        assertThat(printFingerList(new int[]{5}, "left")).isEqualTo("L");
        assertThat(printFingerList(new int[]{8}, "left")).isEqualTo("L");
        assertThat(printFingerList(new int[]{0}, "left")).isEqualTo("L");
    }

    @Test
    void manyButtonNumbersTest() {
        assertThat(printFingerList(new int[]{1, 1}, "right")).isEqualTo("LL");
        assertThat(printFingerList(new int[]{1, 4}, "right")).isEqualTo("LL");
        assertThat(printFingerList(new int[]{4, 7}, "right")).isEqualTo("LL");
        assertThat(printFingerList(new int[]{1, 9}, "right")).isEqualTo("LR");

        assertThat(printFingerList(new int[]{2, 5}, "right")).isEqualTo("RR");

        // 후퇴.. -> 이겼다.
        // 아마 같을 때가 문제일거야.
        assertThat(printFingerList(new int[]{2, 0}, "right")).isEqualTo("RL");

        assertThat(printFingerList(new int[]{1, 4, 7}, "right")).isEqualTo("LLL");

        assertThat(printFingerList(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right")).isEqualTo("LRLLLRLLRRL");
        assertThat(printFingerList(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left")).isEqualTo("LRLLRRLLLRR");
        assertThat(printFingerList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right")).isEqualTo("LLRLLRLLRL");
    }

    @Test
    void calculateDistanceTest() {
        assertThat(calculateDistance("1", 1)).isEqualTo(0);
        assertThat(calculateDistance("7", 1)).isEqualTo(2);
        assertThat(calculateDistance("1", 3)).isEqualTo(2);
        assertThat(calculateDistance("1", 5)).isEqualTo(2);
        assertThat(calculateDistance("*", 5)).isEqualTo(3);
    }

    @Test
    void currentFingersTest() {
        Memory memory = new Memory("*", "#");

        whichFinger("L", 1, memory);
        assertThat(memory.getLeftPosition()).isEqualTo("1");

        whichFinger("L", 3, memory);
        assertThat(memory.getRightPosition()).isEqualTo("3");

        whichFinger("R", 6, memory);
        assertThat(memory.getLeftPosition()).isEqualTo("1");
        assertThat(memory.getRightPosition()).isEqualTo("6");

        whichFinger("L", 2, memory);
        assertThat(memory.getLeftPosition()).isEqualTo("2");
        assertThat(memory.getRightPosition()).isEqualTo("6");

        whichFinger("L", 1, memory);
        assertThat(memory.getLeftPosition()).isEqualTo("1");
        assertThat(memory.getRightPosition()).isEqualTo("6");

        whichFinger("L", 5, memory);
        assertThat(memory.getLeftPosition()).isEqualTo("1");
        assertThat(memory.getRightPosition()).isEqualTo("5");
    }

    // leftDistance => 현재 왼쪽손의 버튼 위치 <-> 목적지 버튼 위치
    // rightDistance => 현재 오른손의 버튼 위치 <-> 목적지 버튼 위치
    // 위치 두개의 거리
    // 두개의 버튼이 있을때의 거리?

    private String printFingerList(int[] numbers, String hand) {
        Memory firstMemory = new Memory("*", "#");

//        StringBuilder result = new StringBuilder();
//        for (int number: numbers) {
//            result.append(whichFinger(hand, number, firstMemory));
//        }
//
//        return result.toString();

        return IntStream.of(numbers)
                .mapToObj((i) -> whichFinger(hand, i, firstMemory))
                .collect(Collectors.joining());
    }

    private String whichFinger(String hand, int number, Memory memory) {
        // number 0~9
        int[] leftButtons = {1, 4, 7};
        int[] rightButtons = {3, 6, 9};

        // firstNumber in {1, 4, 7}
        if (includes(leftButtons, number)) {
            memory.saveLeftFinger(String.valueOf(number));
            return "L";
        }

        // firstNumber in {3, 6, 9}
        if (includes(rightButtons, number)) {
            memory.saveRightFinger(String.valueOf(number));
            return "R";
        }

        // firstNumber in {2, 5, 8, 0}
        int leftDistance = this.calculateDistance(memory.getLeftPosition(), number);
        int rightDistance = this.calculateDistance(memory.getRightPosition(), number);

        if (leftDistance < rightDistance) {
            memory.saveLeftFinger(String.valueOf(number));

            return "L";
        }

        if (leftDistance > rightDistance) {
            memory.saveRightFinger(String.valueOf(number));

            return "R";
        }

        if (hand.equals("left")) {
            memory.saveLeftFinger(String.valueOf(number));
        }

        if (hand.equals("right")) {
            memory.saveRightFinger(String.valueOf(number));
        }

        return hand.substring(0, 1).toUpperCase();
    }

    private int calculateDistance(String currentLeft, int number) {
        HashMap<String, int[]> matrix = new HashMap<>();

        matrix.put("1", new int[]{0, 3});
        matrix.put("2", new int[]{1, 3});
        matrix.put("3", new int[]{2, 3});
        matrix.put("4", new int[]{0, 2});
        matrix.put("5", new int[]{1, 2});
        matrix.put("6", new int[]{2, 2});
        matrix.put("7", new int[]{0, 1});
        matrix.put("8", new int[]{1, 1});
        matrix.put("9", new int[]{2, 1});
        matrix.put("*", new int[]{0, 0});
        matrix.put("0", new int[]{1, 0});
        matrix.put("#", new int[]{2, 0});

        return Math.abs(matrix.get(currentLeft)[1] - matrix.get(String.valueOf(number))[1]) +
                Math.abs(matrix.get(currentLeft)[0] - matrix.get(String.valueOf(number))[0]);
    }

    private boolean includes(int[] leftButtons, int firstNumber) {
        return Arrays.stream(leftButtons).anyMatch(i -> i == firstNumber);
    }
}