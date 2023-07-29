package day06

import DayTest
import org.junit.jupiter.api.Test


class Day06Test : DayTest() {
    private val exampleDay = Day06("06".readTestInput())
    private val day = Day06("06".readInput())

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