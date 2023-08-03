package day08

import Day

class Day08(private val input: String) : Day() {
    override fun part1(): Int {
        var totalChars = 0
        var totalEscapedChars = 0
        input.lines().forEach {
            totalChars += it.length
            var escapedString = it.subSequence(1, it.length - 1).replace(regex = Regex("\\\\\\\""), replacement = "\"")
                .replace(regex = "\\\\\\\\".toRegex(), replacement = "\\\\")
            escapedString = escapedString.replace("\\\\x([0-9|a-f]){2}".toRegex(), "n")
            totalEscapedChars += escapedString.length
        }
        return (totalChars - totalEscapedChars)
    }

    override fun part2(): Int {
        var totalChars = 0
        var totalEscapedChars = 0
        input.lines().forEach {
            totalChars += it.length
            val escapedString = it.replace(regex = Regex("\\\\"), replacement = "oo")
                .replace(regex = "\\\"".toRegex(), replacement = "oo")
            totalEscapedChars += escapedString.length + 2
        }
        return (totalEscapedChars - totalChars)
    }
}