package day24

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day24Test : DayTest() {
    private val exampleDay = Day24("24".readTestInput())
    private val day = Day24("24".readInput())

    @Test
    fun testSolvePart1WithExampleInput() {
        val part1 = solvePart1(exampleDay)
        val expectedSolution = 99
        Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
    }

    @Test
    fun testSolvePart2WithExampleInput() {
        val part2 = solvePart2(exampleDay)
        val expectedSolution = -1
        Assertions.assertEquals(expectedSolution, part2, "Part 2 with example input should equal $expectedSolution")
    }

    @Test
    fun part1() {
       print(day.part1Long())
    }

    @Test
    fun part2() {
        print(day.part2Long())
    }

}