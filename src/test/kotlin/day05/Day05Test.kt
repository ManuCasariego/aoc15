package day05

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day05Test : DayTest() {
    private val exampleDay = Day05("05".readTestInput())
    private val day = Day05("05".readInput())

    @Test
    fun testSolvePart1WithExampleInput() {
        val part1 = solvePart1(exampleDay)
        val expectedSolution = 2
        Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
    }

    @Test
    fun testSolvePart2WithExampleInput() {
        val part2 = solvePart2(exampleDay)
        val expectedSolution = 0
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