package com.example.demo

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class Solution2Test {
    @Test
    fun contextLoad() {
    }

//    @Test
//    fun `미로를 통과하는 경우`() {
//        assertThat(
//            findLeastTime(
//                arrayOf(
//                    "SOOOL",
//                    "XXXXO",
//                    "OOOOO",
//                    "OXXXX",
//                    "OOOOE"
//                )
//            )
//        )
//            .isEqualTo(16)
//    }

    @Test
    fun `가장 가까운 미로를 통과하는 경우`() {
        assertThat(
            findLeastTime(
                arrayOf("SOOOO", "LEOOO")
            )
        ).isEqualTo(2)
    }

    fun findLeastTime(maps: Array<String>): Int {
        val charSize = maps.sumOf { it.length }
        val xCount = maps.sumOf { char -> char.count { it == 'X' } }

        return charSize - xCount - 1
    }
}
