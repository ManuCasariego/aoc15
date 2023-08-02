package day20

import DayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day20Test : DayTest() {
    private val day = Day20("20".readInput())

    @Test
    fun part1() {
        val part1 = solvePart1(day)
        assertEquals(776160, part1)
    }

    @Test
    fun part2() {
        val part2 = solvePart2(day)
        assertEquals(786240, part2)
    }

}