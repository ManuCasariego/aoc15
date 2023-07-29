package day02

import DayTest
import org.junit.jupiter.api.Test


class Day02Test : DayTest() {
    private val exampleDay = Day02("02".readTestInput())
    private val day = Day02("02".readInput())

    @Test
    fun part1Example() {
        val part1 = solvePart1(exampleDay)
        check(part1 == "101")
    }

    @Test
    fun part2Example() {
        val part2 = solvePart2(exampleDay)
        check(part2 == "48")
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