package day13

import Day

class Day13(private val input: String) : Day() {
    override fun part1(): Int {
        // David would gain 48 happiness units by sitting next to Carol.
        // David would lose 65 happiness units by sitting next to Eric.
        val relationships = input.lines().map { Relationship.builder(it) }
        var people = relationships.map { it.person1 }.distinct()
        // brute force
        var maxMaxHappiness = 0
        for (i in 1..1_000_000) {
            var maxHappiness = 0
            people = people.shuffled()
            for (i in people.indices) {
                val person = people[i]
                val nextPerson = people[(i + 1) % people.size]
                val relationship1 = relationships.find { it.person1 == person && it.person2 == nextPerson }!!
                val relationship2 = relationships.find { it.person1 == nextPerson && it.person2 == person }!!
                maxHappiness += relationship1.happiness + relationship2.happiness
            }
            if (maxHappiness > maxMaxHappiness) {
                maxMaxHappiness = maxHappiness
            }
        }
        return maxMaxHappiness
    }

    override fun part2(): Int {
        val relationships = input.lines().map { Relationship.builder(it) }.toMutableList()

        var people = relationships.map { it.person1 }.distinct().toMutableList()
        for (person in people) {
            relationships.add(Relationship.builder("Me would gain 0 happiness units by sitting next to $person"))
            relationships.add(Relationship.builder("$person would gain 0 happiness units by sitting next to Me"))
        }
        people.add("Me")

        // brute force
        var maxMaxHappiness = 0
        for (i in 1..1_000_000) {
            var maxHappiness = 0
            people = people.shuffled().toMutableList()
            for (i in people.indices) {
                val person = people[i]
                val nextPerson = people[(i + 1) % people.size]
                val relationship1 = relationships.find { it.person1 == person && it.person2 == nextPerson }!!
                val relationship2 = relationships.find { it.person1 == nextPerson && it.person2 == person }!!
                maxHappiness += relationship1.happiness + relationship2.happiness
            }
            if (maxHappiness > maxMaxHappiness) {
                maxMaxHappiness = maxHappiness
            }
        }
        return maxMaxHappiness
    }

    data class Relationship(val person1: String, val person2: String, val happiness: Int) {
        companion object {
            fun builder(line: String): Relationship {
                val split = line.split(" ")
                val person1 = split[0]
                val person2 = split[10].replace(".", "")
                val happiness = if (split[2] == "gain") split[3].toInt() else -split[3].toInt()
                return Relationship(person1, person2, happiness)
            }
        }
    }
}