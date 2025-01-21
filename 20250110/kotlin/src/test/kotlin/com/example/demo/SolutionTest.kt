// https://school.programmers.co.kr/learn/courses/30/lessons/181188

package com.example.demo

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class SolutionTest {
    @Test
    fun `타겟이 하나인 경우`() {
        assertThat(minCount(listOf(Target(1, 2)))).isEqualTo(1)
    }

    @Test
    fun `타겟이 둘이고, 겹치지 않는 경우`() {
        assertThat(
            minCount(
                listOf(
                    Target(1, 2),
                    Target(2, 3),
                )
            )
        ).isEqualTo(2)
    }

    @Test
    fun `타겟이 둘이고, 겹치는 경우`() {
        assertThat(
            minCount(
                listOf(
                    Target(1, 3),
                    Target(2, 4),
                )
            )
        ).isEqualTo(1)
    }

    @Test
    fun `타겟이 셋이고, 겹치지 않는 경우`() {
        assertThat(
            minCount(
                listOf(
                    Target(1, 2),
                    Target(2, 3),
                    Target(3, 4),
                )
            )
        ).isEqualTo(3)
    }

    @Test
    fun `타겟이 셋이고, 모두 겹치는 경우`() {
        assertThat(
            minCount(
                listOf(
                    Target(1, 4),
                    Target(1, 3),
                    Target(2, 4),
                )
            )
        ).isEqualTo(1)
    }

    @Test
    fun `타겟이 셋이고, 둘이 겹치는 경우`() {
        assertThat(
            minCount(
                listOf(
                    Target(1, 4),
                    Target(1, 3),
                    Target(5, 6),
                )
            )
        ).isEqualTo(2)

        assertThat(
            minCount(
                listOf(
                    Target(1, 2),
                    Target(2, 4),
                    Target(3, 5),
                )
            )
        ).isEqualTo(2)
    }

    @Test
    fun `타겟이 넷이고, 모두 겹치는 경우`() {
        assertThat(
            minCount(
                listOf(
                    Target(1, 7),
                    Target(1, 6),
                    Target(2, 5),
                    Target(3, 4),
                )
            )
        ).isEqualTo(1)
    }

    @Test
    fun `타겟이 100,000개고, 겹치지 않는 경우`() {
        val count = 100_000
        assertThat(
            minCount((1..count).map { Target(it, it + 1) })
        ).isEqualTo(count)
    }

    @Test
    fun `타겟이 100,000개고, 2개씩 겹치는 경우`() {
        val count = 100_000
        assertThat(
            minCount((1..count).map { Target(it, it + 2) })
        ).isEqualTo(count / 2)
    }

    @Test
    fun intersect() {
        assertThat(Target(1, 3).intersect(Target(2, 4))).isEqualTo(Target(2, 3))
    }

    @Test
    fun isEmpty() {
        assertThat(Target(2, 2).isEmpty).isTrue()
        assertThat(Target(1, 3).isEmpty).isFalse()
    }
}
