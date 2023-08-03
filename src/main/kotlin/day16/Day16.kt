package day16

import Day

class Day16(private val input: String) : Day() {
    override fun part1(): Int {
        val sueThings = mapOf(
            "children" to 3,
            "cats" to 7,
            "samoyeds" to 2,
            "pomeranians" to 3,
            "akitas" to 0,
            "vizslas" to 0,
            "goldfish" to 5,
            "trees" to 3,
            "cars" to 2,
            "perfumes" to 1
        )
        var suesList = input.lines().map { Sue.builder(it) }
        for (sueThingEntry in sueThings.entries) {
            suesList = suesList.filter { it.contains(sueThingEntry.key, sueThingEntry.value) }
        }

        return suesList[0].number
    }

    override fun part2(): Int {
//        In particular, the cats and trees readings indicates that there are greater than that many (due to the unpredictable nuclear decay of cat dander and tree pollen), while the pomeranians and goldfish readings indicate that there are fewer than that many (due to the modial interaction of magnetoreluctance).

//        What is the number of the real Aunt Sue?
        val sueThings = mapOf(
            "children" to 3,
            "samoyeds" to 2,
            "akitas" to 0,
            "vizslas" to 0,
            "cars" to 2,
            "perfumes" to 1
        )
        val greaterSueThings = mapOf(
            "cats" to 7,
            "trees" to 3,
        )
        val lessSueThings = mapOf(
            "pomeranians" to 3,
            "goldfish" to 5,
        )
        var suesList = input.lines().map { Sue.builder(it) }
        for (sueThingEntry in sueThings.entries) {
            suesList = suesList.filter { it.contains(sueThingEntry.key, sueThingEntry.value) }
        }
        for (greaterSueThingEntry in greaterSueThings.entries) {
            suesList = suesList.filter { it.containsGreater(greaterSueThingEntry.key, greaterSueThingEntry.value) }
        }
        for (lessSueThingEntry in lessSueThings.entries) {
            suesList = suesList.filter { it.containsLess(lessSueThingEntry.key, lessSueThingEntry.value) }
        }

        return suesList[0].number

    }

    data class Sue(val number: Int, val things: Map<String, Int>) {
        fun contains(thing: String, quantity: Int): Boolean {
            if (!things.contains(thing)) return true
            return things[thing]!! == quantity
        }

        fun containsGreater(thing: String, quantity: Int): Boolean {
            if (!things.contains(thing)) return true
            return things[thing]!! > quantity
        }

        fun containsLess(thing: String, quantity: Int): Boolean {
            if (!things.contains(thing)) return true
            return things[thing]!! < quantity
        }

        companion object {
            fun builder(line: String): Sue {
                //Sue 19: trees: 3, perfumes: 0, goldfish: 6
                val regex = Regex("Sue (\\d+): (\\w+?): (\\d+), (\\w+?): (\\d+), (\\w+?): (\\d+)")
                val (number, firstThing, noFirstThing, secondThing, noSecondThing, thirdThing, noThirdThing) = regex.find(
                    line
                )!!.destructured
                return Sue(
                    number.toInt(),
                    mapOf(
                        firstThing to noFirstThing.toInt(),
                        secondThing to noSecondThing.toInt(),
                        thirdThing to noThirdThing.toInt()
                    )
                )
            }
        }
    }
}