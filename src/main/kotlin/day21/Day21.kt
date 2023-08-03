package day21

import Day

class Day21(private val input: String) : Day() {

    override fun part1(): Int {
        return buildStateMap().filter { it.value }.map { it.key.sumOf { item -> item.cost } }.min()
    }

    override fun part2(): Int {
        return buildStateMap().filterNot { it.value }.map { it.key.sumOf { item -> item.cost } }.max()
    }


    private fun winFight(boss: Character, player: Character): Boolean {
        //player starts
        var bossHealth = boss.hitPoints
        var playerHealth = player.hitPoints
        while (playerHealth > 0) {
            // player goes
            bossHealth -= kotlin.math.max((player.damage - boss.armor), 1)
            if (bossHealth <= 0) return true
            playerHealth -= kotlin.math.max((boss.damage - player.armor), 1)
        }
        return false
    }

    private fun getRingPermutations(rings: List<Item>): Set<Set<Item>> {
        val ringPermutations = mutableSetOf<MutableSet<Item>>()
        // no rings
        ringPermutations.add(mutableSetOf())
        // 1|2 rings
        rings.forEach { ring1 -> rings.forEach { ring2 -> ringPermutations.add(mutableSetOf(ring1, ring2)) } }
        return ringPermutations
    }

    private fun buildStateMap(): MutableMap<Set<Item>, Boolean> {
        val stateMap = mutableMapOf<Set<Item>, Boolean>()
        val weapons = Item.parseList(
            "Weapons:    Cost  Damage  Armor\n" +
                    "Dagger        8     4       0\n" +
                    "Shortsword   10     5       0\n" +
                    "Warhammer    25     6       0\n" +
                    "Longsword    40     7       0\n" +
                    "Greataxe     74     8       0"
        )
        val armors = Item.parseList(
            "Armor:      Cost  Damage  Armor\n" +
                    "Leather      13     0       1\n" +
                    "Chainmail    31     0       2\n" +
                    "Splintmail   53     0       3\n" +
                    "Bandedmail   75     0       4\n" +
                    "Noarmor       0     0       0\n" +
                    "Platemail   102     0       5"
        )
        val rings = Item.parseList(
            "Rings:      Cost  Damage  Armor\n" +
                    "Damage +1    25     1       0\n" +
                    "Damage +2    50     2       0\n" +
                    "Damage +3   100     3       0\n" +
                    "Defense +1   20     0       1\n" +
                    "Defense +2   40     0       2\n" +
                    "Defense +3   80     0       3"
        )

        val boss = Character.parse(input)
        val player = Character(100, 0, 0)
        for (weapon in weapons) {
            for (ringSet in getRingPermutations(rings)) {
                for (armor in armors) {
                    val itemSet = mutableSetOf(weapon)
                    itemSet.add(armor)
                    itemSet.addAll(ringSet)
                    // we got the player to fight
                    stateMap[itemSet] = winFight(boss = boss, player = player.applyItemSet(itemSet))
                }
            }
        }
        return stateMap
    }

    data class Item(val cost: Int, val damage: Int, val armor: Int) {
        companion object {
            fun parseList(input: String): List<Item> {
                return input.lines().subList(1, input.lines().size).map { parse(it) }
            }

            private fun parse(line: String): Item {
                val (_, cost, damage, armor) = """(\w+)\s*(\d+)\s*(\d+)\s*(\d+)""".toRegex().find(line)!!.destructured
                return Item(cost.toInt(), damage.toInt(), armor.toInt())
            }
        }
    }

    data class Character(val hitPoints: Int, val damage: Int, val armor: Int) {

        private fun applyItem(item: Item): Character {
            return Character(hitPoints, damage + item.damage, armor + item.armor)
        }

        fun applyItemSet(itemSet: MutableSet<Item>): Character {
            var damage = 0
            var armor = 0
            itemSet.forEach {
                damage += it.damage
                armor += it.armor
            }
            return applyItem(Item(0, damage, armor))
        }

        companion object {
            fun parse(input: String): Character {
                //Hit Points: 109
                //Damage: 8
                //Armor: 2
                val lines = input.lines()
                val hitPoints = lines.first().split(" ").last().toInt()
                val damage = lines[1].split(" ").last().toInt()
                val armor = lines.last().split(" ").last().toInt()
                return Character(hitPoints, damage, armor)
            }
        }
    }
}