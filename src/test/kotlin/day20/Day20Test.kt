package day20

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day20Test : DayTest() {
    private val day = Day20("20".readInput())

    @Test
    fun part1() {
        solvePart1(day)
    }

    @Test
    fun part2() {
        solvePart2(day)
    }

}