package day03

import DayTest
import org.junit.jupiter.api.Test


class Day03Test : DayTest() {
    private val exampleDay = Day03("03".readTestInput())
    private val day = Day03("03".readInput())

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