package day10

import Day
import java.lang.StringBuilder

class Day10(private val input: String) : Day() {
    override fun part1(): Int {
        return (1..40).fold(input) {acc, _ -> lookAndSay(acc)}.length
    }

    override fun part2(): Int {
        return (1..50).fold(input) {acc, _ -> lookAndSay(acc)}.length
    }

    private fun lookAndSay(input: String): String {
        val result = StringBuilder()
        var currentChar = input[0]
        var currentCount = 1
        for (char in input.subSequence(1, input.length)) {
            if (char == currentChar) {
                currentCount++
            } else {
                result.append(currentCount).append(currentChar)
                currentCount = 1
                currentChar = char
            }
        }
        result.append(currentCount).append(currentChar)
        return result.toString()
    }
}