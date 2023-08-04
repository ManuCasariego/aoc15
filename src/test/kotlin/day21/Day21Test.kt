package day21

import DayTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day21Test : DayTest() {
    private val day = Day21()

    @Test
    fun part1() {
        val part1 = solvePart1(day)
        assertEquals(111, part1)
    }

    @Test
    fun part2() {
        val part2 = solvePart2(day)
        assertEquals(188, part2)
    }

}