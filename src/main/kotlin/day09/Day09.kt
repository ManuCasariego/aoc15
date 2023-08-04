package day09

import Day

class Day09 : Day(9) {

    override fun part1(): Int {
        cleanUp()
        // dp
        // getting the distances and cities
        inputLines.forEach {
            val split = it.split(" ")
            distances.add(Distance(split[0], split[2], split[4].toInt()))
            cities.add(split[0])
            cities.add(split[2])
        }
        var minimumDistance = Integer.MAX_VALUE
        for (city in cities) {
            minimumDistance = kotlin.math.min(minimumDistance, goEveryWayMinimumPossible(city, mutableSetOf(city)))
        }
        return minimumDistance
    }

    override fun part2(): Int {
        cleanUp()
        inputLines.forEach {
            val split = it.split(" ")
            distances.add(Distance(split[0], split[2], split[4].toInt()))
            cities.add(split[0])
            cities.add(split[2])
        }
        var maximumDistance = 0
        for (city in cities) {
            maximumDistance = kotlin.math.max(maximumDistance, goEveryWayMaximumPossible(city, mutableSetOf(city)))
        }
        return maximumDistance
    }

    private fun cleanUp() {
        cities = mutableSetOf()
        distances = mutableSetOf()
        stateMap = mutableMapOf()
    }

    data class State(val currentCity: String, val visitedCities: MutableSet<String>)
    data class Distance(val from: String, val to: String, val distance: Int)

    private var cities = mutableSetOf<String>()
    private var distances = mutableSetOf<Distance>()
    private var stateMap = mutableMapOf<State, Int>()
    private fun goEveryWayMinimumPossible(currentCity: String, visitedCities: MutableSet<String>): Int {
        val currentState = State(currentCity, visitedCities)
        if (stateMap.contains(currentState)) {
            return stateMap[currentState]!!
        }

        var minimumTravelRemaining = Integer.MAX_VALUE
        for (city in cities.minus(visitedCities).minus(currentCity)) {
            // iterate through every remaining city
            val newVisitedCities = visitedCities.toMutableSet()
            newVisitedCities.add(city)
            val distance = getDistanceBetweenAAndB(city, currentCity)
            minimumTravelRemaining =
                kotlin.math.min(minimumTravelRemaining, distance + goEveryWayMinimumPossible(city, newVisitedCities))
        }
        if (minimumTravelRemaining == Integer.MAX_VALUE) minimumTravelRemaining = 0
        stateMap[currentState] = minimumTravelRemaining
        return minimumTravelRemaining
    }

    private fun goEveryWayMaximumPossible(currentCity: String, visitedCities: MutableSet<String>): Int {
        val currentState = State(currentCity, visitedCities)
        if (stateMap.contains(currentState)) {
            return stateMap[currentState]!!
        }

        var maximumTravelRemaining = 0
        for (city in cities.minus(visitedCities).minus(currentCity)) {
            // iterate through every remaining city
            val newVisitedCities = visitedCities.toMutableSet()
            newVisitedCities.add(city)
            val distance = getDistanceBetweenAAndB(city, currentCity)
            maximumTravelRemaining =
                kotlin.math.max(maximumTravelRemaining, distance + goEveryWayMaximumPossible(city, newVisitedCities))
        }
        stateMap[currentState] = maximumTravelRemaining
        return maximumTravelRemaining
    }

    private fun getDistanceBetweenAAndB(a: String, b: String): Int {
        for (distance in distances) {
            if (distance.to == a && distance.from == b || distance.to == b && distance.from == a) return distance.distance
        }
        return Integer.MAX_VALUE
    }
}