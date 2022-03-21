import java.util.stream.IntStream;

public class FindKim {

    // 1. 엘리먼트 "Kim" 의 index를 반환 한다.
    // 2. 반환 된 index와 문자열을 조합한다.
    // 3. 문자열은 "김서방은 seoul.value(\"Kim\").indexOf()에 있다" 구조를 가진다.
    // 4. "Kim"은 반드시 포함되고, 없거나 중복되는 일은 없습니다.

    private String solution(String[] seoul) {
        
        int index = IntStream.range(0, seoul.length)
                .filter(i -> seoul[i].equals("Kim"))
                .findFirst().getAsInt();

        return String.format("김서방은 %d에 있다", index);
    }
}
