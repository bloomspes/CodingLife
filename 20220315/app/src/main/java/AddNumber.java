import java.util.Arrays;
import java.util.stream.IntStream;

public class AddNumber {
    // 구현
    private Integer sumNotIncludedArrayNumbers(int[] numbers, int initialNumber) {
        return initialNumber - sumNumbers(numbers);
    }

    private Integer sumNumbers(int[] numbers) {
        if (hasNegativeNumber(numbers)) {
            return null;
        }

        return IntStream.of(numbers).reduce(0, (acc, cur) -> acc + cur);
    }

    private Integer run(int[] numbers, Integer sum) {
        if (numbers.length == 0) {
            return sum;
        }

        int current = numbers[0];
        return run(
                Arrays.copyOfRange(numbers, 1, numbers.length),
                sum + current
        );
    }

    private boolean hasNegativeNumber(int[] numbers) {

//        for(int number : numbers){
//            if(number < 0) {
//                return true;
//            }
//        }

//        return IntStream.of(numbers)
//                .anyMatch(i -> i < 0);

        return IntStream.of(numbers).anyMatch(this::NegativeNumber);
    }

    private boolean NegativeNumber(int i) {
        return i < 0;
    }
}
