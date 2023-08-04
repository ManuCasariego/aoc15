package day17

import Day

class Day17 : Day(17) {
    override fun part1(): Int {
        stateMap = mutableMapOf()
        usedContainers = mutableSetOf()

        var i = 0
        val containers = inputLines.map { Container(i++, it.toInt()) }.toSet()
        magic(150, containers)
        return usedContainers.size
    }

    override fun part2(): Int {
        stateMap = mutableMapOf()
        usedContainers = mutableSetOf()

        var i = 0
        val containers = inputLines.map { Container(i++, it.toInt()) }.toSet()
        magic(150, containers)

        val leastAmountOfContainersUsed = usedContainers.maxOf { it.size }
        return usedContainers.count { it.size == leastAmountOfContainersUsed }
    }

    data class State(val goal: Int, val availableContainers: Set<Container>)

    var stateMap = mutableMapOf<State, Int>()
    var usedContainers = mutableSetOf<Set<Container>>()
    private fun magic(goal: Int, availableContainers: Set<Container>): Int {
        if (goal <= 0) return 0
        // choose every container and go down one level
        val state = State(goal, availableContainers)
        if (stateMap.contains(state)) return stateMap[state]!!
        // from any given state, you want to check how many available containers combinations can be fit
        var solution = 0
        availableContainers.filter { it.quantity <= goal }.forEach {
            if (it.quantity == goal) {
                if (usedContainers.contains(availableContainers.minus(it))) {

                } else {
                    solution++
                    usedContainers.add(availableContainers.minus(it))
                }
            }
            // check if we already got that from some other path, if so, don't add 1
            else solution += magic(goal - it.quantity, availableContainers.minus(it))
        }
        stateMap[state] = solution
        return solution
    }

    data class Container(val index: Int, val quantity: Int)
}