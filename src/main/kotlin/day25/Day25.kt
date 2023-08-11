package day25

import Day

class Day25(private val input: String) : Day() {
    fun part1Long(): Long {
        val regex =
            """To continue, please consult the code grid in the manual. {2}Enter the code at row (\d+), column (\d+).""".toRegex()

        val (rowStr, columnStr) = regex.find(input)!!.destructured
        val row = rowStr.toInt()
        val column = columnStr.toInt()

        // we need to turn the combination of row, number into an actual index
        // notation row, column
        //    | 1   2   3   4   5   6
        //---+---+---+---+---+---+---+
        // 1 |  1   3   6  10  15  21
        // 2 |  2   5   9  14  20
        // 3 |  4   8  13  19
        // 4 |  7  12  18
        // 5 | 11  17
        // 6 | 16

        // 00 -> 1, 1
        // 01 -> 2, 1
        // 02 -> 1, 2
        // 03 -> 3, 1
        // 04 -> 2, 2
        // 05 -> 1, 3
        // 06 -> 4, 1
        // 07 -> 3, 2
        // 08 -> 2, 3
        // 09 -> 1, 4
        // 10 -> 5, 1

        val index = getIndex(row, column)
        return (1..index).fold(20151125L) { agg, _ -> nextPwd(agg) }
    }

    private fun getIndex(row: Int, column: Int): Int {
        val loop = row + (column - 1)
        return (1..<loop).sum() + (column - 1)
    }

    private fun nextPwd(prev: Long): Long {
        return prev * 252533 % 33554393
    }

    override fun part1(): Int {
        TODO("No need to implement")
    }


    override fun part2(): Int {
        TODO("No need to implement")
    }
}