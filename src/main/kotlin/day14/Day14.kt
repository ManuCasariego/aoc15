package day14

import Day

class Day14(private val input: String) : Day() {
    override fun part1(): Int {
        val reindeers = input.lines().map { Reindeer.builder(it) }
        var maxDistance = 0
        val distanceAfter = 2503
        for (reindeer in reindeers) {
            maxDistance = kotlin.math.max(maxDistance, reindeer.distanceAfter(distanceAfter))
        }
        return maxDistance
    }

    override fun part2(): Int {
        val reindeers = input.lines().map { Reindeer.builder(it) }
        val distanceAfter = 2503
        val points = mutableMapOf<String, Int>()
        for (reindeer in reindeers) {
            points[reindeer.name] = 0
        }
        for (second in 1..distanceAfter) {
            var maxDistance = 0
            val leaders = mutableListOf<String>()
            for (reindeer in reindeers) {
                val distance = reindeer.distanceAfter(second)
                if (distance > maxDistance) {
                    maxDistance = distance
                    leaders.clear()
                    leaders.add(reindeer.name)
                } else if (distance == maxDistance) {
                    leaders.add(reindeer.name)
                }
            }
            for (leader in leaders) {
                points[leader] = points[leader]!! + 1
            }
        }
        return points.values.max()
    }

    data class Reindeer(val name: String, val speed: Int, val flyTime: Int, val restTime: Int) {
        fun distanceAfter(seconds: Int): Int {
            val cycleTime = flyTime + restTime
            val cycles = seconds / cycleTime
            val remainingSeconds = seconds % cycleTime
            val remainingFlyTime = if (remainingSeconds > flyTime) flyTime else remainingSeconds
            return (cycles * flyTime + remainingFlyTime) * speed
        }

        companion object {
            fun builder(line: String): Reindeer {
                val split = line.split(" ")
                return Reindeer(split[0], split[3].toInt(), split[6].toInt(), split[13].toInt())
            }
        }

    }
}