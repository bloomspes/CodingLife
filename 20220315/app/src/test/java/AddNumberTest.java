import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


// 0 ~ 9까지의 수 중 일부 만이 numbers 라는 배열에 저장 됨.
// 우리가 찾으려는 것: numbers에 없는 수를 더해서 리턴하라.

// 현아님의 접근 (난 이렇게 풀고싶다!)

// 1. numbers가 배열이기 때문에, 정렬을 사용하고 싶다.
// 2. for 문을 돌면서, 배열에 없는 수를 찾아낸다.
// 3. 배열에 없는 수를 각각 더한다.

// 내가 생각하는 현아님 풀이의 장점
// 배열에 없는 수를 각각 구해서 리스트 혹은 어레이로 반환할수도 있다는 장점.
// 정석적인 풀이라서, 나중에 충분히 문제가 변형되었을 때 응용이 가능하다.

// 나의 접근

// 문제에서 구하려는 것은 숫자의 합
// 0부터 9까지의 수의 합 = 44로 고정됨
// return (44 - number의 원소의 합)

// 서현아가 생각하는 슬기님 풀이의 장점
// 배열로 풀어야된다는 고정관념을 깨뜨렸다.
// 대단..

@DisplayName("AddNumber 클래스")
class AddNumberTest {

    // 테스트
    @Test
    void sumNumbersTest() {
        assertThat(sumNumbers(new int[]{1})).isEqualTo(1);
        assertThat(sumNumbers(new int[]{2})).isEqualTo(2);
        assertThat(sumNumbers(new int[]{3})).isEqualTo(3);
        assertThat(sumNumbers(new int[]{0, 1})).isEqualTo(1);
        assertThat(sumNumbers(new int[]{2, 3})).isEqualTo(5);
        assertThat(sumNumbers(new int[]{0, 1, 9})).isEqualTo(10);
        assertThat(sumNumbers(new int[]{-1})).isNull();
        assertThat(sumNumbers(new int[]{-2})).isNull();
        assertThat(sumNumbers(new int[]{-2, -1})).isNull();
        assertThat(sumNumbers(new int[]{-3, -4})).isNull();
        assertThat(sumNumbers(new int[]{3, -4})).isNull();
        assertThat(sumNumbers(new int[]{3, -4, 5})).isNull();

    }

    @Test
    void hasNegativeNumberTest() {
        assertThat(hasNegativeNumber(new int[]{-1})).isEqualTo(true);

        assertThat(hasNegativeNumber(new int[]{2})).isEqualTo(false);
    }

    @Test
    void sumNotIncludedArrayNumbers() {
        assertThat(sumNotIncludedArrayNumbers(new int[]{1}, 45)).isEqualTo(45 - 1);
        assertThat(sumNotIncludedArrayNumbers(new int[]{1, 2}, 45)).isEqualTo(45 - 1 - 2);
        assertThat(sumNotIncludedArrayNumbers(new int[]{1, 2, 3, 4, 6, 7, 8, 0}, 45)).isEqualTo(14);
        assertThat(sumNotIncludedArrayNumbers(new int[]{5, 8, 4, 0, 6, 7, 9}, 45)).isEqualTo(6);
    }


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

/**
 * 메소드 참조 ::(더블 콜론)
 * 메소드 참조 4종류
 * static 메소드 참조 : ContaningClass::staticMethodName
 * 특정 객체의 인스턴스 메소드 참조 : contaningObject::instanceMethodName
 * 특정 유형의 임의의 객체에 대한 인스턴스 메소드 참조 : ContaningType::methodName
 * 생성자 참조 : ClassName::new
 */