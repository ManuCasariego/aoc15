package day02

import DayTest
import org.junit.jupiter.api.Test


class Day02Test : DayTest() {
    private val exampleDay = Day02(
    """
        2x3x4
        1x1x10
    """.trimIndent())
    private val day = Day02("02".readInput())

    @Test
    fun part1Example() {
        solvePart1(exampleDay)
    }

    @Test
    fun part2Example() {
        solvePart2(exampleDay)
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