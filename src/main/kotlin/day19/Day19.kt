package day19

import Day
import java.util.Stack

class Day19(private val input: String) : Day() {
  override fun part1(): Int {
    val replacements = input.split("\n\n")[0].lines().map { Replacement.parse(it) }
    val molecule = input.split("\n\n")[1]

    return replacements.map { applyReplacement(it, molecule) }.flatten().toSet().size
  }

  override fun part2(): Int {
    val reverseReplacements = input.split("\n\n")[0].lines().map { Replacement.parseReverse(it) }
    val molecule = input.split("\n\n")[1]

    val levels = mutableListOf<Int>()

    // let's reverse it, start from the molecule, and work down towards "e"
    // using a queue, so I can prioritise going down in size
    val stack = Stack<String>()
    stack.add(molecule)
    val numberOfTransformationsMap = mutableMapOf(molecule to 0)
    while (stack.isNotEmpty() && !stack.contains("e")) {
      val current = stack.pop()
      val shortestTwo =
        reverseReplacements.map { applyReplacement(it, current) }.flatten().toSet().sortedBy { it.length }.take(2).filter {!numberOfTransformationsMap.contains(it)}
          .toList()
      stack.addAll(shortestTwo)
      levels.add(numberOfTransformationsMap[current]!!)
      shortestTwo.forEach { numberOfTransformationsMap.putIfAbsent(it, numberOfTransformationsMap[current]!! + 1)}
    }
    println(levels)
    return numberOfTransformationsMap.getOrDefault("e", -1)
  }

  private fun applyReplacement(replacement: Replacement, molecule: String): Set<String> {
    return replacement.from.findAll(molecule).map { molecule.replaceRange(it.range, replacement.to) }.toSet()
  }


  data class Replacement(val from: Regex, val to: String) {
    companion object {
      fun parse(line: String): Replacement {
        // Ca => PRnFAr
        val (from, to) = """(\w+) => (\w+)""".toRegex().find(line)!!.destructured
        return Replacement(from.toRegex(), to)
      }

      fun parseReverse(line: String): Replacement {
        // Ca => PRnFAr
        val (to, from) = """(\w+) => (\w+)""".toRegex().find(line)!!.destructured
        return Replacement(from.toRegex(), to)
      }
    }
  }
}