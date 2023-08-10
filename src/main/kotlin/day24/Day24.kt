package day24

import Day

class Day24(private val input: String) : Day() {
    override fun part1(): Int {
        val totalWeight = input.lines().sumOf { it.toInt() }
        // each group needs to weight totalWeight / 3
        for (i in 1..input.lines().size / 3) {

        }

    }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }
}