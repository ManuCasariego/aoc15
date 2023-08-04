package day19

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day19Test : DayTest() {
  private val exampleDay = Day19()
  private val day = Day19()

  @Test
  fun testSolvePart1WithExampleInput() {
    val part1 = solvePart1(exampleDay)
    val expectedSolution = 7
    Assertions.assertEquals(expectedSolution, part1, "Part 1 with example input should equal $expectedSolution")
  }

  @Test
  fun testSolvePart2WithExampleInput() {
    val part2 = solvePart2(exampleDay)
    val expectedSolution = 6
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