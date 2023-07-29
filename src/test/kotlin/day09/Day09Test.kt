package day09

import DayTest
import day09.Day09
import org.junit.jupiter.api.Test


class Day09Test : DayTest() {
    private val exampleDay = Day09("09".readTestInput())
    private val day = Day09("09".readInput())

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