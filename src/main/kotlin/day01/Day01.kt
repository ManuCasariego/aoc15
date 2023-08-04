package day01

import Day

class Day01(test: Boolean = false) : Day(1, test) {
    override fun part1(): Int {
        return inputString.map { if (it == '(') 1 else if (it == ')') -1 else 0 }.sum()
    }

    override fun part2(): Int {
        var position = 1
        var level = 0
        inputString.forEach { char ->
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