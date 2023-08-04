import java.io.File

abstract class Day(dayNumber: Int, test: Boolean = false) {

    protected val inputLines: List<String> = getInputAsList(dayNumber, test)
    protected val inputString: String = getInputAsString(dayNumber, test)
    abstract fun part1(): Int
    abstract fun part2(): Int

    companion object {
        private fun getInputAsList(day: Int, test:Boolean): List<String> {
            return File("src/test/kotlin/day${String.format("%02d", day)}/${if (test)"test" else ""}input").readText().lines()
        }

        private fun getInputAsString(day:Int,test:Boolean) :String {
            return File("src/test/kotlin/day${String.format("%02d", day)}/${if (test)"test" else ""}input").readText()
        }
    }
}