package day05

import Day

class Day05(private val input: String) : Day() {
    override fun part1(): String {
        return input.lines().filter {
            isNicePart1(it)
        }.size.toString()

    }

    private fun isNicePart1(name: String): Boolean {
        val chars = name.toCharArray()
        // less than three vowels returns false
        if (name.filter { ((it == 'a') or (it == 'e') or (it == 'i') or (it == 'o') or (it == 'u')) }.length < 3) return false

        // at least one letter that appears twice in a row
        var condition = false
        for (i in 0..name.length - 2) {
            if (chars[i] == chars[i + 1]) condition = true
        }
        if (!condition) return false

        // it does not contain ab, cd, pq, or xy
        if (name.contains("ab") or name.contains("cd") or name.contains("pq") or name.contains("xy")) return false

        return true
    }

    override fun part2(): String {
        return input.lines().filter {
            isNicePart2(it)
        }.size.toString()
    }

    data class CharPair(val a: Char, val b: Char)

    private fun isNicePart2(name: String): Boolean {
        val chars = name.toCharArray()
        if (name.length < 3) return false

        val charMap = mutableMapOf<CharPair, Int>()
        // It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
        var condition = false
        for (i in 0..name.length - 2) {
            val charPair = CharPair(chars[i], chars[i + 1])
            if (charMap.contains(charPair)) {
                if (charMap[charPair]!! < i - 1) condition = true
            } else {
                charMap[charPair] = i
            }
        }
        if (!condition) return false

        // It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
        for (i in 0..name.length - 3) {
            if (chars[i] == chars[i + 2]) return true
        }
        return false
    }
}