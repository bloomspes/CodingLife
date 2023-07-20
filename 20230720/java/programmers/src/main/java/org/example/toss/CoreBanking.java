package org.example.toss;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class CoreBanking {
    int solution(String sequence) {
        OptionalInt fancyNumber = IntStream.range(0, sequence.length() - 2)
                .filter(i -> isFancyNumbers(sequence, i))
                .map(i -> Integer.parseInt(sequence.substring(i, i + 3)))
                .max();

        return fancyNumber.orElse(-1);
    }

    private boolean isFancyNumbers(String sequence, int index) {
        char first = sequence.charAt(index);
        char middle = sequence.charAt(index + 1);
        char last = sequence.charAt(index + 2);

        return first == middle && middle == last;
    }

}
