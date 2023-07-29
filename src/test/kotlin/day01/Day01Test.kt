package day01

import DayTest
import org.junit.jupiter.api.Test


class Day01Test : DayTest() {
    private val exampleDay = Day01("01".readTestInput())
    private val day = Day01("01".readInput())

    @Test
    fun part1Example() {
        val part1 = solvePart1(exampleDay)
        check(part1 == "-1")
    }

    @Test
    fun part2Example() {
        val part2 = solvePart2(exampleDay)
        check(part2 == "5")
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