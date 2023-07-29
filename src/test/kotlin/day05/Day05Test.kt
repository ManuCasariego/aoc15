package day05

import DayTest
import day05.Day05
import org.junit.jupiter.api.Test


class Day05Test : DayTest() {
    private val exampleDay = Day05("05".readTestInput())
    private val day = Day05("05".readInput())

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