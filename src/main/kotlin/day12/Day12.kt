package day12

import Day
import kotlin.streams.asStream

class Day12 : Day(12) {
    override fun part1(): Int {
        return getSumFromJson(inputString)
    }

    override fun part2(): Int {
        var variableInput = inputString
        while (variableInput.contains(":\"red\"")) {
            val r = variableInput.indexOf(":\"red\"")
            // I need to find an opening key going left
            // and a closing key going right
            // every time I find the other one the I will need to skip one more
            val left = findOpeningKeyLeft(variableInput, r)
            val right = findClosingKeyRight(variableInput, r)
            variableInput = variableInput.substring(0, left) + variableInput.substring(right + 1, variableInput.length)
        }
        return getSumFromJson(variableInput)
    }

    private fun getSumFromJson(input: String): Int {
        val regex = "(-)?\\d+".toRegex()
        val matchResults = regex.findAll(input)
        return matchResults.asStream().mapToInt { it.value.toInt() }.sum()
    }

    private fun findOpeningKeyLeft(variableInput: String, r: Int): Int {
        var leftIndex = r - 1
        var jokers = 0
        while (true) {
            if (variableInput[leftIndex] == '{') {
                // found it
                if (jokers == 0) {
                    /// truly found it
                    return leftIndex
                } else jokers--
            } else if (variableInput[leftIndex] == '}') {
                jokers++
            }
            leftIndex--
        }
    }

    private fun findClosingKeyRight(variableInput: String, r: Int): Int {
        var rightIndex = r + 1
        var jokers = 0
        while (true) {
            if (variableInput[rightIndex] == '}') {
                // found it
                if (jokers == 0) {
                    /// truly found it
                    return rightIndex
                } else jokers--
            } else if (variableInput[rightIndex] == '{') {
                jokers++
            }
            rightIndex++
        }
    }
}