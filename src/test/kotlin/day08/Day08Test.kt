package day08

import DayTest
import org.junit.jupiter.api.Test


class Day08Test : DayTest() {
    private val exampleDay = Day08("08".readTestInput())
    private val day = Day08("08".readInput())

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