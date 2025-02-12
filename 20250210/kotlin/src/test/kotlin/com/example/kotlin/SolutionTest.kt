package com.example.kotlin

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class SolutionTest {
    @Test
    fun `경주가 끝났을 때 해설진이 이름을 부르는 순서`() {
        assertThat(
            solution(
                arrayOf("mumu", "soe", "poe"),
                arrayOf("soe")
            )
        ).isEqualTo(arrayOf("soe", "mumu", "poe"))
    }

    fun solution(
        players: Array<String>,
        callings: Array<String>,
    ): Array<String> {
        val rank = HashMap<String, Int>()

        players.forEachIndexed { index, player ->
            rank[player] = index
        }

        callings.forEach { player ->
            swap(rank, player, players)
        }

        return players
    }

    fun swap(
        rank: HashMap<String, Int>,
        player: String,
        players: Array<String>
    ) {
        val currentRank = rank[player] ?: return

        if (currentRank == 0) return

        val frontPlayerRank = currentRank - 1
        val frontPlayer = players[frontPlayerRank]

        players[frontPlayerRank] = player
        players[currentRank] = frontPlayer

        rank[player] = frontPlayerRank
        rank[frontPlayer] = currentRank
    }
}
