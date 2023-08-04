package day11

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day11Test : DayTest() {
    private val exampleDay = Day11()
    private val day = Day11()

    @Test
    fun testSolvePart1WithExampleInput() {
        val part1 = exampleDay.part1String()
        val expectedSolution = "abcdffaa"
        Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
    }

    @Test
    fun testSolvePart2WithExampleInput() {
        val part2 = exampleDay.part2String()
        val expectedSolution = "abcdffbb"
        Assertions.assertEquals(expectedSolution, part2, "Part 2 with example input should equal $expectedSolution")
    }

    @Test
    fun part1() {
        val part1 = day.part1String()
        println("the solution to part 1 is $part1")
        assertEquals("vzbxxyzz", part1)
    }

    @Test
    fun part2() {
        val part2 = day.part2String()
        println("the solution to part 2 is $part2")
        assertEquals("vzcaabcc", part2)
    }

}