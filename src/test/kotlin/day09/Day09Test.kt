package day09

import DayTest
import day09.Day09
import org.junit.jupiter.api.Test


class Day09Test : DayTest() {
    private val exampleDay = Day09("example")
    private val day = Day09("09".readInput())

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