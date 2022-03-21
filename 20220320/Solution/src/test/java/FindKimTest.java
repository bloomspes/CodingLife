
// 구하려는 것: String 배열의 엘리먼트인 Kim의 index를 구해서, 김서방 위치(index)를 포함한 문자열을 리턴한다

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("서울에서 김서방 찾기 문제")
class FindKimTest {
    @Test
    void 김서방_위치를_반환하는_문자열을_완성하라() {
        assertThat(solution(new String[]{"Jane", "Kim"})).isEqualTo("김서방은 1에 있다");
        assertThat(solution(new String[]{"Kim", "Jane"})).isEqualTo("김서방은 0에 있다");
    }

    private String solution(String[] seoul) {
        // 1. 엘리먼트 "Kim" 의 index를 반환 한다.
        // 2. 반환 된 index와 문자열을 조합한다.
        // 3. 문자열은 "김서방은 seoul.value(\"Kim\").indexOf()에 있다" 구조를 가진다.
        // 4. "Kim"은 반드시 포함되고, 없거나 중복되는 일은 없습니다.

        int index = IntStream.range(0, seoul.length)
                .filter(i -> seoul[i].equals("Kim"))
                .findFirst().getAsInt();

        return String.format("김서방은 %d에 있다", index);
    }
}
