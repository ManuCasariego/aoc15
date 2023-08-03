package day18

import Day

class Day18(private val input: String) : Day() {
    override fun part1(): Int {
        var board = input.lines().map { it.map { c -> c == '#' }.toMutableList() }.toMutableList()
        for (i in 1..100) {
            board = nextStep(board)
        }
        return board.flatten().count { it }
    }

    override fun part2(): Int {
        var board = input.lines().map { it.map { c -> c == '#' }.toMutableList() }.toMutableList()
        for (i in 1..100) {
            board[0][0] = true
            board[99][0] = true
            board[0][99] = true
            board[99][99] = true
            board = nextStep(board)
        }
        board[0][0] = true
        board[99][0] = true
        board[0][99] = true
        board[99][99] = true
        return board.flatten().count { it }
    }

    private fun nextStep(
        board: MutableList<MutableList<Boolean>>
    ): MutableList<MutableList<Boolean>> {
        val newBoard = MutableList(board.size) { MutableList(board[0].size) { false } }
        for (y in newBoard.indices) {
            for (x in newBoard[y].indices) {
                if (board[y][x]) {
                    // it's on
                    val onsAround = onsAround(board, y, x)
                    newBoard[y][x] = (onsAround == 2 || onsAround == 3)
                } else {
                    // it's off
                    val onsAround = onsAround(board, y, x)
                    newBoard[y][x] = (onsAround == 3)
                }
            }
        }
        return newBoard
    }

    private fun onsAround(board: List<List<Boolean>>, y: Int, x: Int): Int {
        var count = 0
        if (y + 1 in board.indices && x - 1 in board[y].indices) {
            if (board[y + 1][x - 1]) count++
        }
        if (y + 1 in board.indices && x in board[y].indices) {
            if (board[y + 1][x]) count++
        }
        if (y + 1 in board.indices && x + 1 in board[y].indices) {
            if (board[y + 1][x + 1]) count++
        }
        if (y in board.indices && x - 1 in board[y].indices) {
            if (board[y][x - 1]) count++
        }
        if (y in board.indices && x + 1 in board[y].indices) {
            if (board[y][x + 1]) count++
        }
        if (y - 1 in board.indices && x - 1 in board[y].indices) {
            if (board[y - 1][x - 1]) count++
        }
        if (y - 1 in board.indices && x in board[y].indices) {
            if (board[y - 1][x]) count++
        }
        if (y - 1 in board.indices && x + 1 in board[y].indices) {
            if (board[y - 1][x + 1]) count++
        }
        return count
    }
}