package codingLife.chapter02

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class Chapter02Test {
    fun sum(n: Long): Long {
        tailrec fun go(n: Long, acc: Long): Long =
            if (n == 0L) acc
            else go(n - 1, acc + n)
        return go(n, 0)
    }

    @Test
    fun testSum() {
        assertEquals(1, sum(1))
        assertEquals(3, sum(2))
        assertEquals(6, sum(3))
    }

    fun fibonacci(n: Long): Long {
        if (n <= 1) return n

        tailrec fun go(n: Long, acc: Long, previous: Long): Long =
            if (n == 1L) acc
            else go(n - 1, acc + previous, acc)

        return go(n, 1, 0)
    }

    @Test
    fun testFibonacci() {
        assertEquals(0, fibonacci(0))
        assertEquals(1, fibonacci(1))
        assertEquals(1, fibonacci(2))
    }

    val <T> List<T>.head: T
        get() = first()

    val <T> List<T>.tail: List<T>
        get() = drop(1)

    fun <A> isSorted(
        xs: List<A>,
        order: (A, A) -> Boolean
    ): Boolean {
        if (xs.isEmpty()) return true

        tailrec fun go(head: A, tail: List<A>): Boolean = when {
            tail.isEmpty() -> true
            !order(head, tail.head) -> false
            else -> go(tail.head, tail.tail)
        }

        return go(xs.head, xs.tail)
    }

    @Test
    fun testIsSortedIntList() {
        val order = { a: Int, b: Int -> (a <= b) }

        assertTrue(isSorted(emptyList(), order))
        assertTrue(isSorted(listOf(1, 2, 3), order))
        assertFalse(isSorted(listOf(1, 3, 2), order))
        assertFalse(isSorted(listOf(2, 1, 3), order))
    }
}
