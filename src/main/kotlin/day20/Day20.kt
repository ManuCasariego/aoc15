package day20

import Day
import kotlin.math.sqrt

class Day20(private val input: String) : Day() {
    override fun part1(): Int {
        val goal = input.lines().first().toInt()
        var i = (goal / 43)
        while (i < goal / 10) {
            val giftsForHouse = getDivisors(i).sum() * 10
            if (giftsForHouse >= goal) return i
            i++
        }
        return -1
    }

    override fun part2(): Int {
        val goal = input.lines().first().toInt()
        var i = (goal / 43)
        while (i < goal / 11) {
            val giftsForHouse = getDivisorsPart2(i).sum() * 11
            if (giftsForHouse >= goal) return i
            i++
        }
        return -1
    }

    private fun getDivisors(n: Int): MutableSet<Int> {
        val divisors = mutableSetOf<Int>()
        for (i in 1..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) divisors.addAll(listOf(i, n / i))
        }
        return divisors
    }

    private fun getDivisorsPart2(n: Int): MutableSet<Int> {
        val divisors = mutableSetOf<Int>()
        for (i in 1..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) divisors.addAll(listOf(i, n / i).filter { it * 50 >= n })
        }
        return divisors
    }

}