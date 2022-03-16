
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.HashMap;


import static org.assertj.core.api.Assertions.assertThat;

// 왼손 엄지손가락 = '*', 오른손 엄지손가락 = '#'

// 1, 4, 7 => 왼손 엄지손가락 사용
// 3, 6, 9 => 오른손 엄지손가락 사용
// 2, 5, 8, 0 => 두 엄지손가락의 위치에서 더 가까운 엄지손가락을 사용
// 그런데, 두 엄지손가락 위치가 서로 같으면,
// 왼손잡이 인 경우에는 왼손 엄지손가락을 사용하고, 오른손잡이 인 경우에는 오른손 엄지손가락을 사용.

// 구해야 할 것: 눌러야 할 엄지손가락을 모아서 문자열로 리턴하자.

class SolutionTest {
    @Test
    void whichFirstButtonNumbersTest() {
        assertThat(printFingerList(new int[]{1}, "right")).isEqualTo("L");
        assertThat(printFingerList(new int[]{4}, "right")).isEqualTo("L");
        assertThat(printFingerList(new int[]{7}, "right")).isEqualTo("L");

        assertThat(printFingerList(new int[]{3}, "right")).isEqualTo("R");
        assertThat(printFingerList(new int[]{6}, "left")).isEqualTo("R");
        assertThat(printFingerList(new int[]{9}, "right")).isEqualTo("R");

        assertThat(printFingerList(new int[]{2}, "right")).isEqualTo("R");
        assertThat(printFingerList(new int[]{5}, "left")).isEqualTo("L");
        assertThat(printFingerList(new int[]{8}, "left")).isEqualTo("L");
        assertThat(printFingerList(new int[]{0}, "left")).isEqualTo("L");
    }

    @Test
    void ManyButtonNumbersTest() {
        assertThat(printFingerList(new int[]{1, 1}, "right")).isEqualTo("LL");
        assertThat(printFingerList(new int[]{1, 4}, "right")).isEqualTo("LL");
        assertThat(printFingerList(new int[]{4, 7}, "right")).isEqualTo("LL");
        assertThat(printFingerList(new int[]{1, 9}, "right")).isEqualTo("LR");

        assertThat(printFingerList(new int[]{2, 5}, "right")).isEqualTo("RR");

        // 후퇴..
//        assertThat(printFingerList(new int[]{2, 0}, "right")).isEqualTo("RL");
    }

    @Test
    void calculateDistanceTest() {
        assertThat(calculateDistance("1", 1)).isEqualTo(0);
        assertThat(calculateDistance("7", 1)).isEqualTo(2);
        assertThat(calculateDistance("1", 3)).isEqualTo(2);
        assertThat(calculateDistance("1", 5)).isEqualTo(2);
        assertThat(calculateDistance("*", 5)).isEqualTo(3);
    }

    // leftDistance => 현재 왼쪽손의 버튼 위치 <-> 목적지 버튼 위치
    // rightDistance => 현재 오른손의 버튼 위치 <-> 목적지 버튼 위치
    // 위치 두개의 거리
    // 두개의 버튼이 있을때의 거리?

    private String printFingerList(int[] numbers, String hand) {
        if (numbers.length == 1) {
            return whichFinger(numbers, hand, numbers[0]);
        }

        if (numbers.length == 2) {
            return whichFinger(numbers, hand, numbers[0]) + whichFinger(numbers, hand, numbers[1]);
        }

        return null;
    }

    private String whichFinger(int[] numbers, String hand, int number) {
        int[] leftButtons = {1, 4, 7};
        int[] rightButtons = {3, 6, 9};

        int[] buttons = {2, 5, 8, 0};

        // firstNumber in {1, 4, 7}
        if (includes(leftButtons, number)) {
            return "L";
        }

        // firstNumber in {3, 6, 9}
        if (includes(rightButtons, number)) {
            return "R";
        }

        // firstNumber in {2, 5, 8, 0}
        String currentLeft = "1";
        String currentRight = "4";

        if (includes(buttons, number)) {
            if (calculateDistance(currentLeft, number) == calculateDistance(currentRight, number)) {
                return hand.substring(0, 1).toUpperCase();
            }

            return null;
        }

        return null;
    }

    // ~~거리 계산을 제대로 해주기~~
    // 손의 위치를 업데이트 해주기
    private int calculateDistance(String currentLeft, int number) {
        // 숫자의 위치 -> 좌표
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