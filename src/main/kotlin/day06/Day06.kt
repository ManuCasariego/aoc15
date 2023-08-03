package day06

import Day
import kotlin.math.max

class Day06(private val input: String) : Day() {

    override fun part1(): Int {
        val (sizeX, sizeY) = listOf(1000, 1000)
        val board = Board(Array(sizeX) { Array(sizeY) { false } })
        input.lines().map(Instruction.Companion::parse).forEach(board::execute)
        return board.howManyLightsOn()
    }

    override fun part2(): Int {
        val (sizeX, sizeY) = listOf(1000, 1000)
        val board = BoardPart2(Array(sizeX) { Array(sizeY) { 0 } })
        input.lines().map(Instruction.Companion::parse).forEach(board::execute)
        return board.howManyLightsOn()
    }

    data class Coordinate(val x: Int, val y: Int) {
        companion object {
            fun builder(line: String): Coordinate {
                val split = line.split(",")
                return Coordinate(split[0].toInt(), split[1].toInt())
            }
        }
    }

    data class Board(val board: Array<Array<Boolean>>) {
        private fun turnOn(a: Coordinate, b: Coordinate) {
            for (iterableX in a.x..b.x) for (iterableY in a.y..b.y) board[iterableX][iterableY] = true
        }

        private fun turnOff(a: Coordinate, b: Coordinate) {
            for (iterableX in a.x..b.x) for (iterableY in a.y..b.y) board[iterableX][iterableY] = false
        }

        private fun toggle(a: Coordinate, b: Coordinate) {
            for (iterableX in a.x..b.x) for (iterableY in a.y..b.y) board[iterableX][iterableY] =
                !board[iterableX][iterableY]
        }

        fun execute(instruction: Instruction) {
            when (instruction.operation) {
                "turn on" -> this.turnOn(instruction.a, instruction.b)
                "turn off" -> this.turnOff(instruction.a, instruction.b)
                else -> this.toggle(instruction.a, instruction.b)
            }
        }

        fun howManyLightsOn(): Int {
            return board.flatten().count { it }
        }
    }

    data class BoardPart2(val board: Array<Array<Int>>) {
        private fun turnOn(a: Coordinate, b: Coordinate) {
            for (iterableX in a.x..b.x) for (iterableY in a.y..b.y) board[iterableX][iterableY] += 1
        }

        private fun turnOff(a: Coordinate, b: Coordinate) {
            for (iterableX in a.x..b.x) for (iterableY in a.y..b.y) board[iterableX][iterableY] =
                max(0, board[iterableX][iterableY] - 1)
        }

        private fun toggle(a: Coordinate, b: Coordinate) {
            for (iterableX in a.x..b.x) for (iterableY in a.y..b.y) board[iterableX][iterableY] += 2
        }

        fun execute(instruction: Instruction) {
            when (instruction.operation) {
                "turn on" -> this.turnOn(instruction.a, instruction.b)
                "turn off" -> this.turnOff(instruction.a, instruction.b)
                else -> this.toggle(instruction.a, instruction.b)
            }
        }

        fun howManyLightsOn(): Int {
            return board.flatten().sum()
        }
    }

    data class Instruction(val operation: String, val a: Coordinate, val b: Coordinate) {
        companion object {
            fun parse(line: String): Instruction {
                val split = line.split(" ")
                return if (line.startsWith("turn on")) {
                    // turn on operation
                    Instruction("turn on", Coordinate.builder(split[2]), Coordinate.builder(split[4]))
                } else if (line.startsWith("turn off")) {
                    // turn off operation
                    Instruction("turn off", Coordinate.builder(split[2]), Coordinate.builder(split[4]))
                } else {
                    // toggle operation
                    Instruction("toggle", Coordinate.builder(split[1]), Coordinate.builder(split[3]))
                }
            }
        }
    }

}