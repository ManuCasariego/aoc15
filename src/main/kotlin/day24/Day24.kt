package day24

import Day

class Day24(private val input: String) : Day() {
    fun part1Long(): Long {
        return solveDay24(3)
    }

    override fun part1(): Int {
        return 0
    }

    override fun part2(): Int {
        return 0
    }

    fun part2Long(): Long {
        return solveDay24(4)
    }

    private fun solveDay24(noOfSleighs: Int): Long {
        val totalWeight = input.lines().sumOf { it.toInt() }
        for (i in 1..input.lines().size / noOfSleighs) {
            val permutationsValues = calculatePermutationsValues(input.lines().map { it.toInt() }, i)
            val solution =
                permutationsValues.filter { it.sum() == totalWeight / noOfSleighs }
                    .map { it.map { another -> another.toLong() }.reduce { acc, i -> acc * i } }.sorted()
            if (solution.isNotEmpty()) return solution[0]
        }
        return -1
    }

    private fun calculatePermutationsValues(list: List<Int>, n: Int): List<List<Int>> {
        return calculatePermutationsIndex(list.size, n).map { innerList -> innerList.map { list[it] } }
    }

    private fun calculatePermutationsIndex(listLength: Int, n: Int): List<List<Int>> {
        val permutations = mutableListOf<MutableList<Int>>()
        var previous = if (n != 1) calculatePermutationsIndex(listLength, n - 1) else mutableListOf(mutableListOf())
        for (i in 0..<listLength) {
            previous = previous.filterNot { it.contains(i) }
            for (item in previous) {
                val currList = mutableListOf(i)
                currList.addAll(item)
                permutations.add(currList)
            }
        }
        return permutations
    }

}