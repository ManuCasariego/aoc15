package day21

import DayTest
import org.junit.jupiter.api.Test


class Day21Test : DayTest() {
    private val exampleDay = Day21("example")
    private val day = Day21("21".readInput())

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