import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.lang.Long.sum;
import static org.assertj.core.api.Assertions.assertThat;

// 문자의 갯수가 반복되는 경우, 반복되는 갯수를 숫자로 표기하여 문자열을 리턴한다.
// 리턴한 문자열 중에 가장 짧은 문자열을 구하는게 목표.


// abab
// 2ab
// 1개 단위로 잘라서, 표현하는 방법 => abab
// 2개 단위로 잘라서, 표현하는 방법 => 2ab

// 조건을 놓친게 있었을까?
// 문자열은 순서를 지켜야 한다.
// abab의 경우 하나의 문자열로 자를 때 abab가 나와야 한다.

// 그러면 조건을 포괄하는 계획을 어떻게 세울 수 있을까?

// String letter = "abaaba" 인 경우, 2aba가 나오겠지만
// abccccca ab4ca / staraaort 인 경우라면...

// 자꾸 압축과 중복을 혼동하고 있었다. (문제를 해결 하기 힘들었던 이유)
// 1개 단위로 압축을 할 수 있는 가장 간단한 케이스를 생각해보자.
// aaaa = 4a
// 하나의 문자열로 이루어진 경우 길이를 리턴하는 방법부터 통과를 시켜보자.

// "(전체 문자열의 길이) + Set<문자열>"의 길이를 구하자.
// 이를 단순화 한다면, 하나의 문자열로 이루어진 경우라면 무조건 Set<문자열> 갯수는 1 이기 때문에...
// "(문자열 갯수) + 1".length 를 리턴해 줘야 한다.


//   [a, a, b, c]
//    a

//    map{a: 1}
//       a map{a: 2}
//          b map{b: 1 a: 2}
//              c map{c: 1 b: 1 a: 2}

//  길이를 구하면 되니까 꺼내는데 순서는 따로 보장하지 않아도 됨.
//  {a: 2}, {b: 1}, {c: 1}

//  value == 1인 경우, key만 출력.
// "2a", "b", "c"
// "2abc"

// "2abc".length = 4

class SolutionTest {

    @Test
    void aabc의_압축_길이를_구하라() {
        assertThat(Compressed("aabc")).isEqualTo(4);
    }

    private int Compressed(String letters) {
        List<String> sliced = List.of(letters.split(""));

        Map<String, Integer> map = new HashMap<>();

        ArrayList<Integer> body = new ArrayList<>();

        for (String letter : sliced) {
            map.merge(letter, 1, Integer::sum);
        }

        System.out.println(map);

        // map 출력 - 길이만 뽑을 거니까... 갯수만 카운트 해주자. ㅎㅎ
        for (String key : map.keySet()) {
            body.add(map.get(key));

        }
        System.out.println(body);

        return body.stream().mapToInt(Integer::intValue).sum();
    }

//    @Test
//    void 문자열을_슬라이싱_한다() {
//        assertThat(sliced("aabb")).isEqualTo(new String[]{"a", "a", "b", "b"});
//        assertThat(sliced("aabbaccc")).isEqualTo(new String[]{"a", "a", "b", "b", "a", "c", "c", "c"});
//    }
//
//    @Test
//    void 중복된_문자열을_숫자로_치환한다() {
//        assertThat(replaceToNumbers("aabb")).isEqualTo(4);
//    }
//
//    @Test
//    void 문자열을_비교하여_중복된_갯수를_찾는다() {
//        assertThat(findDuplicated("aabb")).isEqualTo(new int[]{2, 2});
//    }

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