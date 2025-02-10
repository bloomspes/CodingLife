// https://school.programmers.co.kr/learn/courses/30/lessons/134240

package com.example.kotlin

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class Solution2Test {
    @Test
    fun `수용이가 준비한 음식의 양이 2가지 인 경우`() {
        assertThat(solution(intArrayOf(1, 2, 3))).isEqualTo("12021")
    }

    @Test
    fun `수용이가 준비한 음식의 양이 3가지 인 경우`() {
        assertThat(solution(intArrayOf(1, 3, 4, 6))).isEqualTo("1223330333221")
        assertThat(solution(intArrayOf(1, 7, 1, 2))).isEqualTo("111303111")

    }

    private fun solution(food: IntArray): String {
        val left = leftSide(food)
        val right = rightSide(left)

        return left + "0" + right
    }

    private fun leftSide(food: IntArray): String {
        val range = 1 until food.size

        return range.joinToString(separator = "") {
            it.toString().repeat(food[it] / 2)
        }
    }

    private fun rightSide(left: String): String {
        return left.asSequence()
            .fold(ArrayDeque<Char>()) { deque, c -> deque.apply { add(c) } }
            .let { deque: ArrayDeque<Char> ->
                val range = (1..deque.size)
                range.map { deque.removeLast() }
                    .joinToString("")
            }
    }

}
