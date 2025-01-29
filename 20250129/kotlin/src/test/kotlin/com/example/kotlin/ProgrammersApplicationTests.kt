// 프로그래머스
// https://school.programmers.co.kr/learn/courses/30/lessons/147355?language=kotlin
package com.example.kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class ProgrammersApplicationTests {
    @Test
    fun contextLoads() {
    }

    @Test
    fun `t의 길이가 1인 부분 문자열의 갯수`() {
        assertThat(solution("500220839878", "7")).isEqualTo(8)
    }

    @Test
    fun `t의 길이가 2인 부분 문자열의 갯수`() {
        assertThat(solution("10203", "15")).isEqualTo(3)
    }

    @Test
    fun `t의 길이가 3인 부분 문자열의 갯수`() {
        assertThat(solution("3141592", "271")).isEqualTo(2)
    }

    fun solution(text: String, pattern: String): Int {
        val windowSize = pattern.length
        val range = 0..(text.length - windowSize)

        return range.count { isMatch(text, it, windowSize, pattern) }
    }

    private fun isMatch(
        text: String,
        start: Int,
        size: Int,
        pattern: String
    ): Boolean {
        return text.substring(start, start + size) <= pattern
    }
}
