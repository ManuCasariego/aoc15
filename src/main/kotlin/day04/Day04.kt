package day04

import Day
import java.math.BigInteger
import java.security.MessageDigest

class Day04 : Day(4) {
    override fun part1(): Int {
        for (i in 0..1_000_000) {
            if ("$inputString$i".md5().startsWith("00000")) return i
        }
        return -1
    }

    override fun part2(): Int {
        for (i in 0..10_000_000) {
            if ("$inputString$i".md5().startsWith("000000")) return i
        }
        return -1
    }

    private fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')
}