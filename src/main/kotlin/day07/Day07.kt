package day07

import Day
import java.util.*
import kotlin.collections.Map
import kotlin.math.abs
import kotlin.math.pow

class Day07(private val input: String) : Day() {

    override fun part1(): Int {
        return buildMapForSignalAndReturnA(input)
    }

    override fun part2(): Int {
        val firstA = buildMapForSignalAndReturnA(input)
        return buildMapForSignalAndReturnA(input, initialValueForB = firstA)
    }

    private fun buildMapForSignalAndReturnA(
        input: String,
        initialValueForB: Int? = null,
        signal: String = "a",
    ): Int {
        val signalMap = mutableMapOf<String, Signal>()
        if (initialValueForB != null) signalMap["b"] = Signal.builder(initialValueForB)
        val instructionMap = buildInstructionMap(input)
        val instructionStack = Stack<String>()
        instructionStack.push(instructionMap[signal])
        while (instructionStack.isNotEmpty()) {
            val instruction = instructionStack.peek()
            val splitInstruction = instruction.split(" ")
            // evaluate
            if (instruction.contains("OR") || instruction.contains("AND")) {
                val a = getSignal(signalMap, splitInstruction[0])
                val b = getSignal(signalMap, splitInstruction[2])
                if (a == null) {
                    instructionStack.push(instructionMap[splitInstruction[0]])
                }
                if (b == null) {
                    instructionStack.push(instructionMap[splitInstruction[2]])
                }
                if (a != null && b != null) {
                    if (instruction.contains("OR")) signalMap[splitInstruction[4]] = a.or(b)
                    else signalMap[splitInstruction[4]] = a.and(b)
                    instructionStack.pop()
                }
            } else if (instruction.contains("NOT")) {
                val a = getSignal(signalMap, splitInstruction[1])
                if (a == null) {
                    instructionStack.push(instructionMap[splitInstruction[1]])
                }
                if (a != null) {
                    signalMap[splitInstruction[3]] = a.not()
                    instructionStack.pop()
                }
            } else if (instruction.contains("SHIFT")) {
                val a = getSignal(signalMap, splitInstruction[0])
                if (a == null) {
                    instructionStack.push(instructionMap[splitInstruction[0]])
                }
                if (a != null) {
                    if (instruction.contains("LSHIFT")) signalMap[splitInstruction[4]] =
                        a.lShift(splitInstruction[2].toInt())
                    else signalMap[splitInstruction[4]] = a.rShift(splitInstruction[2].toInt())
                    instructionStack.pop()
                }
            } else {
                // set operation
                val a = getSignal(signalMap, splitInstruction[0])
                if (a == null) {
                    instructionStack.push(instructionMap[splitInstruction[0]])
                }
                if (a != null) {
                    signalMap[splitInstruction[2]] = a.copy()
                    instructionStack.pop()
                }
            }
        }
        return signalMap["a"]!!.toInt()
    }

    private fun buildInstructionMap(input: String): MutableMap<String, String> {
        val instructionMap = mutableMapOf<String, String>()
        input.lines().forEach {
            val split = it.split(" ")
            instructionMap[split.last()] = it
        }
        return instructionMap
    }


    private fun getSignal(map: Map<String, Signal>, string: String): Signal? {
        // three options:
        // A. it's already on the map
        if (map.containsKey(string)) return map[string]!!
        // B. it's a numeric value
        if (string[0].isDigit()) return Signal.builder(string.toInt())
        // C. the instruction should be blocked as we don't have this value yet
        return null
    }

    data class Signal(val array: Array<Boolean>) {
        companion object {
            fun builder(): Signal {
                return Signal(Array(16) { false })
            }

            fun builder(int: Int): Signal {
                return Signal(Array(16) { i -> ((int shr (15 - i)) and 1) == 1 })
            }
        }

        fun copy(): Signal {
            return Signal(array)
        }

        fun and(other: Signal): Signal {
            val result = builder()
            for (i in array.indices) {
                result.array[i] = this.array[i] and other.array[i]
            }
            return result
        }

        fun or(other: Signal): Signal {
            val result = builder()
            for (i in array.indices) {
                result.array[i] = this.array[i] or other.array[i]
            }
            return result
        }

        fun not(): Signal {
            val result = builder()
            for (i in array.indices) {
                result.array[i] = !this.array[i]
            }
            return result
        }

        private fun rShift(): Signal {
            val result = builder()

            for (i in 1..<array.size) {
                result.array[i] = this.array[i - 1]
            }
            return result
        }

        fun rShift(int: Int): Signal {
            var intermediate = this
            for (i in 0..<int) intermediate = intermediate.rShift()
            return intermediate
        }

        private fun lShift(): Signal {
            val result = builder()
            for (i in 0..<array.size - 1) {
                result.array[i] = this.array[i + 1]
            }
            return result
        }

        fun lShift(int: Int): Signal {
            var intermediate = this
            for (i in 0..<int) intermediate = intermediate.lShift()
            return intermediate
        }

        fun toInt(): Int {
            var count = 0
            for (i in array.indices) {
                if (array[i]) {
                    val power = abs(i - (array.size - 1))
                    count += 2.0.pow(power.toDouble()).toInt()
                }
            }
            return count
        }
    }

}