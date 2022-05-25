import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] solution(int[] array, int[][] commands) {
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

    public int[] sliceArray(int[] array, int start, int end) {
        // array index 시작 위치: commands[0][0] - 1
        // array index 종료 위치: commands[0][1]
        return Arrays.copyOfRange(array, start, end);
    }
}
