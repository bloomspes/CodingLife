package org.example.toss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoreBankingTest {
    // Given
    CoreBanking coreBanking = new CoreBanking();

    @Test
    @DisplayName("멋쟁이 숫자가 오직 하나인 케이스")
    void singleTimeFancyNumbers(){
        assertThat(coreBanking.solution("12223")).isEqualTo(222);
        assertThat(coreBanking.solution("12333")).isEqualTo(333);
        assertThat(coreBanking.solution("33344")).isEqualTo(333);
        assertThat(coreBanking.solution("344444")).isEqualTo(444);
        assertThat(coreBanking.solution("35554")).isEqualTo(555);
    }

    @Test
    @DisplayName("멋쟁이 숫자가 두 개 이상 존재하는 케이스")
    void multiFancyNumbers() {
        assertThat(coreBanking.solution("111222")).isEqualTo(222);
        assertThat(coreBanking.solution("222111")).isEqualTo(222);
        assertThat(coreBanking.solution("111999333")).isEqualTo(999);
    }

    @Test
    @DisplayName("멋쟁이 숫자가 000이면, 0을 리턴하는 케이스")
    void fancyNumbersMadeOfOnlyZeroValue() {
        assertThat(coreBanking.solution("00099")).isEqualTo(0);
        assertThat(coreBanking.solution("99000")).isEqualTo(0);
    }

    @Test
    @DisplayName("멋쟁이 숫자가 존재하지 않는 케이스")
    void noFancyNumbers() {
        assertThat(coreBanking.solution("123")).isEqualTo(-1);
        assertThat(coreBanking.solution("122345")).isEqualTo(-1);
        assertThat(coreBanking.solution("12345")).isEqualTo(-1);
    }
}
