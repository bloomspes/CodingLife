package com.example.demo

import kotlin.math.max
import kotlin.math.min

data class Target(
    val start: Int,
    val end: Int,
) {
    val isEmpty: Boolean
        get() = start >= end

    fun intersect(other: Target): Target {
        return Target(max(start, other.start), min(end, other.end))
    }
}

fun minCount(targets: List<Target>): Int {
    if (targets.isEmpty()) {
        return 0
    }

    var minCount = 0
    var index = 0

    while (index < targets.size) {
        var intersection = targets[index]
        index += 1
        for (i in index until targets.size) {
            val target = targets[i]
            if (intersection.end <= target.start) {
                break
            }
            intersection = intersection.intersect(target)
            index += 1
        }
        minCount += 1
    }

    return minCount
}

class Solution {
    fun solution(targets: Array<IntArray>): Int {
        return minCount(
            targets.map { Target(it[0], it[1]) }.sortedBy { it.start }
        )
    }
}
