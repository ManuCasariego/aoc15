package day11

import DayTest
import org.junit.jupiter.api.Test


class Day11Test : DayTest() {
    private val exampleDay = Day11("11".readTestInput())
    private val day = Day11("11".readInput())

    @Test
    fun part1Example() {
        val part1 = solvePart1(exampleDay)
        check(part1 == "")
    }

    @Test
    fun part2Example() {
        val part2 = solvePart2(exampleDay)
        check(part2 == "")
    }

    @Test
    fun part1() {
        solvePart1(day)
    }

    @Test
    fun part2() {
        solvePart2(day)
    }

}