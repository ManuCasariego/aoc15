package day20

import Day

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
        val divisors = mutableSetOf(n, 1)
        for (i in 2..n / 2) {
            if (n % i == 0) divisors.add(i)
        }
        return divisors
    }


    private fun getDivisorsPart2(n: Int): MutableSet<Int> {
        val divisors = mutableSetOf(n)

        for (i in n / 50..n / 2) {
            if (n % i == 0 && i * 50 >= n) divisors.add(i)
        }
        return divisors
    }
}