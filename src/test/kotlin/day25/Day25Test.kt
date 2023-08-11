package day25

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day25Test : DayTest() {
    private val exampleDay = Day25("25".readTestInput())
    private val day = Day25("25".readInput())

    @Test
    fun testSolvePart1WithExampleInput() {
        val part1 = exampleDay.part1Long()
        val expectedSolution = 27995004L
        Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
    }

    @Test
    fun part1() {
        val part1 = day.part1Long()
        val expectedSolution = 8997277L
        Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
    }
}