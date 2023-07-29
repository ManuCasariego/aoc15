package day03

import Day

class Day03(private val input: String) : Day() {

    data class Point(val x: Int, val y: Int)

    override fun part1(): String {
        val houses = mutableSetOf<Point>()
        var (currentX, currentY) = listOf(0, 0)
        houses.add(Point(currentX, currentY))
        input.forEach { c: Char ->
            when (c) {
                '>' -> currentX += 1
                '<' -> currentX -= 1
                'v' -> currentY -= 1
                '^' -> currentY += 1
            }
            houses.add(Point(currentX, currentY))
        }
        return houses.size.toString()
    }

    override fun part2(): String {
        val houses = mutableSetOf<Point>()
        var (currentSantaX, currentSantaY) = listOf(0, 0)
        var (currentDoggyX, currentDoggyY) = listOf(0, 0)
        houses.add(Point(currentSantaX, currentSantaY))
        var santaTurn = true
        input.forEach { c: Char ->
            if (c == '>') if (santaTurn) currentSantaX++ else currentDoggyX++
            else if (c == '<') if (santaTurn) currentSantaX-- else currentDoggyX--
            else if (c == 'v') if (santaTurn) currentSantaY-- else currentDoggyY--
            else if (c == '^') if (santaTurn) currentSantaY++ else currentDoggyY++
            if (santaTurn) houses.add(Point(currentSantaX, currentSantaY)) else houses.add(
                Point(
                    currentDoggyX, currentDoggyY
                )
            )
            santaTurn = !santaTurn
        }
        return houses.size.toString()
    }
}