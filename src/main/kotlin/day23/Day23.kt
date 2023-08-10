package day23

import Day

class Day23(private val input: String) : Day() {
    override fun part1(): Int {
        return calculateValueOfB(0, 0)
    }

    override fun part2(): Int {
        return calculateValueOfB(1, 0)
    }

    private fun calculateValueOfB(startingA: Int, startingB: Int): Int {

        // multiple instructions
        // hlf r -> sets r to half
        // tpl r -> sets r to triple itself
        // inc r -> increases r by 1
        // jmp offset -> jumps to instruction offset steps away
        // jie r, offset -> jump if r is even
        // jio r, offset -> jump if r is one
        // if you get an undefined instruction, you shall stop
        var a = startingA
        var b = startingB

        // how to iterate the lines
        val lines = input.lines()
        var currLine = 0

        while (true) {
            if (currLine >= lines.size) return b
            val currInstruction = lines[currLine]
            val doesItAffectA = currInstruction.split(" ")[1].replace(",", "") == "a"
            when {
                currInstruction.startsWith("hlf") -> {
                    if (doesItAffectA) a /= 2
                    else b /= 2
                    currLine++
                }

                currInstruction.startsWith("tpl") -> {
                    if (doesItAffectA) a *= 3
                    else b *= 3
                    currLine++
                }

                currInstruction.startsWith("inc") -> {
                    if (doesItAffectA) a += 1
                    else b += 1
                    currLine++
                }

                currInstruction.startsWith("jmp") -> {
                    val toJump = currInstruction.split(" ")[1].toInt()
                    currLine += toJump
                }

                currInstruction.startsWith("jie") -> {
                    val toJump = currInstruction.split(" ")[2].toInt()
                    if (doesItAffectA && a % 2 == 0) currLine += toJump
                    else if (!doesItAffectA && b % 2 == 0) currLine += toJump
                    else currLine++
                }

                currInstruction.startsWith("jio") -> {
                    val toJump = currInstruction.split(" ")[2].toInt()
                    if (doesItAffectA && a == 1) currLine += toJump
                    else if (!doesItAffectA && b == 1) currLine += toJump
                    else currLine++
                }

                else -> {
                    return b
                }

            }

        }
    }
}