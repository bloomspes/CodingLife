// N마리의 폰켓몬의 종류가 담긴 배열이 주어질 때,
// 가장 많은 종류의 폰켓몬을 선택해서 그 수를 리턴하라.

// 내가 선택할 수 있는 폰켓몬 가짓수는 (전체 배열의 길이) / 2 가짓 수 이다.
// 폰켓몬 종류의 수는 HashSet을 사용해서 추려낸다.
// 종류의 수가 내가 선택할 수 있는 가짓수보다 크면 내가 선택할 수 있는 가짓 수를 리턴한다.
// 반대로, 내가 선택할 수 있는 가짓수가 종류의 수보다 크면 종류의 수를 리턴한다.


import java.util.HashSet;
import java.util.stream.IntStream;

public class Solution {
    public int solution(int[] ponkemons) {
        HashSet<Integer> type = new HashSet<>();
        int amounts = ponkemons.length / 2;

        IntStream.of(ponkemons).forEach(monster -> type.add(monster));

        if (type.size() > amounts) {
            return amounts;
        } else {
            return type.size();
        }
    }
}
