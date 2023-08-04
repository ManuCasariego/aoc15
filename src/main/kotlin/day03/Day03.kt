package day03

import Day

class Day03 : Day(3) {

    data class Point(val x: Int, val y: Int)

    override fun part1(): Int {
        val houses = mutableSetOf<Point>()
        var (currentX, currentY) = listOf(0, 0)
        houses.add(Point(currentX, currentY))
        inputString.forEach { c: Char ->
            when (c) {
                '>' -> currentX += 1
                '<' -> currentX -= 1
                'v' -> currentY -= 1
                '^' -> currentY += 1
            }
            houses.add(Point(currentX, currentY))
        }
        return houses.size
    }

    override fun part2(): Int {
        val houses = mutableSetOf<Point>()
        var (currentSantaX, currentSantaY) = listOf(0, 0)
        var (currentDoggyX, currentDoggyY) = listOf(0, 0)
        houses.add(Point(currentSantaX, currentSantaY))
        var santaTurn = true
        inputString.forEach { c: Char ->
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
        return houses.size
    }
}