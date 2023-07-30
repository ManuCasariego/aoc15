package day02

import Day
import kotlin.math.min

class Day02(private val input: String) : Day() {
    override fun part1(): Int {
        var total = 0
        for (line in input.lines()) {
            val (l, w, h) = line.split('x').map { it.toInt() }
            val first = 2 * l * w
            val second = 2 * l * h
            val third = 2 * w * h
            total += min(min(first, second), third) / 2 + first + second + third
        }
        return total
    }

    override fun part2(): Int {

        var total = 0
        for (line in input.lines()) {
            val (l, w, h) = line.split('x').map { it.toInt() }
            val bow = l * w * h
            val first = 2 * (l + w)
            val second = 2 * (l + h)
            val third = 2 * (w + h)
            total += bow + min(min(first, second), third)
        }
        return total
    }
}