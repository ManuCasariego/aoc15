package day01

import Day

class Day01(private val input: String) : Day() {
    override fun part1(): Int {
        var level = 0
        input.forEach {
            if (it == '(') {
                level++
            } else if (it == ')')
                level--
        }
        return level
    }

    override fun part2(): Int{
        var position = 1
        var level = 0
        input.forEach { char ->
            if (char == '(') {
                level++
            } else if (char == ')') {
                level--
            }
            if (level == -1) {
                return position
            }
            position++
        }
        return -1
    }
}