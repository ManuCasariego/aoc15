import java.io.File

open class DayTest {

    protected fun solvePart1(day: Day): Int {
        val start = System.currentTimeMillis()
        val part1 = day.part1()
        println("Part 1: $part1 in ${System.currentTimeMillis() - start}ms")
        return part1
    }

    protected fun solvePart2(day: Day): Int {
        val start = System.currentTimeMillis()
        val part2 = day.part2()
        println("Part 2: $part2 in ${System.currentTimeMillis() - start}ms")
        return part2
    }
}