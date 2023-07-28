package day05

import DayTest
import day05.Day05
import org.junit.jupiter.api.Test


class Day05Test : DayTest() {
    private val exampleDay = Day05("example")
    private val day = Day05("05".readInput())

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