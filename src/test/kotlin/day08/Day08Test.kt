package day08

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day08Test : DayTest() {
    private val exampleDay = Day08("08".readTestInput())
    private val day = Day08("08".readInput())

    @Test
    fun testSolvePart1WithExampleInput() {
        val part1 = solvePart1(exampleDay)
        val expectedSolution = 12
        Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
    }

    @Test
    fun testSolvePart2WithExampleInput() {
        val part2 = solvePart2(exampleDay)
        val expectedSolution = 19
        Assertions.assertEquals(expectedSolution, part2, "Part 2 with example input should equal $expectedSolution")
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