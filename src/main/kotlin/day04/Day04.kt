package day04

import Day
import helper.md5

class Day04(private val input: String) : Day() {
    override fun part1(): Int {
        for (i in 0..1_000_000) {
            if ((input + i.toString()).md5() .startsWith("00000")) return i
        }
        return -1
    }

    override fun part2(): Int {
        for (i in 0..10_000_000) {
            if ((input + i.toString()).md5() .startsWith("000000")) return i
        }
        return -1
    }

}