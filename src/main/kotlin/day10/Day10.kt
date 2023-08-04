package day10

import Day
import java.lang.StringBuilder

class Day10 : Day(10) {
    override fun part1(): Int {
        // apply process 40 times
        var intermediateInput = inputString
        for (i in 1..40) {
            intermediateInput = lookAndSay(intermediateInput)
        }
        return intermediateInput.length
    }

    override fun part2(): Int {
        // apply process 50 times
        var intermediateInput = inputString
        for (i in 1..50) {
            intermediateInput = lookAndSay(intermediateInput)
        }
        return intermediateInput.length
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