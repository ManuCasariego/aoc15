package day15

import Day

class Day15(private val input: String) : Day() {
    override fun part1(): Int {
        val ingredients = input.lines().map { Ingredient.builder(it) }
        return getBestCookie(ingredients)
    }

    override fun part2(): Int {
        val ingredients = input.lines().map { Ingredient.builder(it) }
        return getBestCookie(ingredients, caloriesExpected = 500)
    }

    private fun getBestCookie(ingredients: List<Ingredient>, caloriesExpected: Int = -1): Int {
        // maximum capacity 100 spoons
        val numberOfSpoons = 100
        var maxPoints = 0
        for (i in 0..numberOfSpoons)
            for (j in 0..numberOfSpoons - i)
                for (k in 0..numberOfSpoons - i - j) {
                    val l = numberOfSpoons - i - j - k
                    val capacity: Int =
                        i * ingredients[0].capacity + j * ingredients[1].capacity + k * ingredients[2].capacity + l * ingredients[3].capacity
                    val durability: Int =
                        i * ingredients[0].durability + j * ingredients[1].durability + k * ingredients[2].durability + l * ingredients[3].durability
                    val flavour =
                        i * ingredients[0].flavour + j * ingredients[1].flavour + k * ingredients[2].flavour + l * ingredients[3].flavour
                    val texture =
                        i * ingredients[0].texture + j * ingredients[1].texture + k * ingredients[2].texture + l * ingredients[3].texture
                    val calories =
                        i * ingredients[0].calories + j * ingredients[1].calories + k * ingredients[2].calories + l * ingredients[3].calories
                    if (capacity <= 0 || durability <= 0 || flavour <= 0 || texture <= 0) continue
                    if (caloriesExpected != -1 && calories != caloriesExpected) continue
                    val points = capacity * durability * flavour * texture
                    maxPoints = kotlin.math.max(maxPoints, points)
                }
        return maxPoints
    }


    data class Ingredient(
        val name: String,
        val capacity: Int,
        val durability: Int,
        val flavour: Int,
        val texture: Int,
        val calories: Int
    ) {
        companion object {
            fun builder(line: String): Ingredient {
                val split = line.split(" ")
                val name = split[0].replace(":", "")
                val capacity = split[2].replace(",", "").toInt()
                val durability = split[4].replace(",", "").toInt()
                val flavour = split[6].replace(",", "").toInt()
                val texture = split[8].replace(",", "").toInt()
                val calories = split[10].replace(",", "").toInt()
                return Ingredient(name, capacity, durability, flavour, texture, calories)
            }
        }
    }
}