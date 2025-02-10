// https://school.programmers.co.kr/learn/courses/30/lessons/161989

package com.example.kotlin

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class SolutionTest {
    @Test
    fun `페인트 칠해야 하는 구역이 2곳일 때`() {
        assertThat(solution(4, intArrayOf(1, 3))).isEqualTo(1)
    }

    data class State(
        val coverMax: Int,
        val count: Int,
    )

    fun solution(rollerSize: Int, section: IntArray): Int {
        val paint = section.fold(State(coverMax = 0, count = 0)) {
            (cover, count), sector ->
            if (sector <= cover) {
                State(cover, count)
            } else {
                State(sector + rollerSize - 1, count + 1)
            }
        }

        return paint.count
    }
}
