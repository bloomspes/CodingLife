import java.util.stream.IntStream;

public class Solution {

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
