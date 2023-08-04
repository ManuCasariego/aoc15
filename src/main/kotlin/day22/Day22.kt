package day22

import Day

class Day22(private val input: String) : Day() {
    override fun part1(): Int {
        val boss = Character.parse(input)
        val player = Character(50, 0, 0, 500)
        stateMap = mutableMapOf()
        takeChoice(player, boss, 0, 0,0,0)
        return stateMap.filter { it.value }.minOf { it.key }
    }

    override fun part2(): Int {
        val boss = Character.parse(input)
        val player = Character(50, 0, 0, 500)
        stateMap = mutableMapOf()
        takeChoice(player, boss, 0, 0,0,0, true)
        return stateMap.filter { it.value }.minOf { it.key }
    }


    var stateMap = mutableMapOf<Int, Boolean>()


    private fun takeChoice(
        player: Character,
        boss: Character,
        manaUsed: Int,
        shieldTimer: Int,
        rechargeTimer: Int,
        poisonTimer: Int,
        lose1HealthPerTurn: Boolean = false
    ) {
        val possibleChoices = mutableSetOf<Spell>()
        if (player.mana >= Spell.MAGIC_MISSILE.manaCost) possibleChoices.add(Spell.MAGIC_MISSILE)
        if (player.mana >= Spell.DRAIN.manaCost) possibleChoices.add(Spell.DRAIN)
        if (player.mana >= Spell.SHIELD.manaCost && shieldTimer <= 1) possibleChoices.add(Spell.SHIELD)
        if (player.mana >= Spell.POISON.manaCost && poisonTimer <= 1) possibleChoices.add(Spell.POISON)
        if (player.mana >= Spell.RECHARGE.manaCost && rechargeTimer <= 1) possibleChoices.add(Spell.RECHARGE)
        possibleChoices.forEach { playTurn(player.copy(), boss.copy(), manaUsed, shieldTimer, rechargeTimer, poisonTimer, it, lose1HealthPerTurn) }
    }


    private fun playTurn(
        player: Character,
        boss: Character,
        manaUsed: Int,
        shieldTimer: Int,
        rechargeTimer: Int,
        poisonTimer: Int,
        action: Spell,
        lose1HealthPerTurn: Boolean = false
    ) {
        var variableRechargeTimer = rechargeTimer
        var variablePoisonTimer = poisonTimer
        var variableShieldTimer = shieldTimer

        // start of turn effects
        if (variableRechargeTimer > 0) {
            player.mana += 101
            variableRechargeTimer--
        }
        if (variableShieldTimer > 0) {
            variableShieldTimer--
            if (variableShieldTimer == 0) player.armor -= 7
        }
        if (variablePoisonTimer > 0) {
            variablePoisonTimer--
            boss.hitPoints -= 3
        }

        if (lose1HealthPerTurn) player.hitPoints -= 1
        if (player.hitPoints <= 0) return
        // player turn
        when (action) {
            Spell.MAGIC_MISSILE -> {
                boss.hitPoints -= 4
            }

            Spell.DRAIN -> {
                boss.hitPoints -= 2
                player.hitPoints += 2
            }

            Spell.SHIELD -> {
                variableShieldTimer = 6
                player.armor = 7
            }

            Spell.POISON -> {
                variablePoisonTimer = 6
            }

            Spell.RECHARGE -> {
                variableRechargeTimer = 5
            }
        }
        player.mana -= action.manaCost

        // start of turn effects
        if (variableRechargeTimer > 0) {
            player.mana += 101
            variableRechargeTimer--
        }
        if (variableShieldTimer > 0) {
            variableShieldTimer--
            if (variableShieldTimer == 0) player.armor = 0
        }
        if (variablePoisonTimer > 0) {
            variablePoisonTimer--
            boss.hitPoints -= 3
        }

        if (boss.hitPoints <= 0) {
            stateMap[manaUsed + action.manaCost] = true
            return
        }
        player.hitPoints -= kotlin.math.max((boss.damage - player.armor), 1)
        if (player.hitPoints <= 0) return
        takeChoice(
            player,
            boss,
            manaUsed + action.manaCost,
            variableShieldTimer,
            variableRechargeTimer,
            variablePoisonTimer,
            lose1HealthPerTurn
        )
    }

    data class Character(var hitPoints: Int, var damage: Int, var armor: Int, var mana: Int) {

        companion object {
            fun parse(input: String): Character {
                //Hit Points: 109
                //Damage: 8
                val hitPoints = input.lines().first().split(" ").last().toInt()
                val damage = input.lines()[1].split(" ").last().toInt()
                return Character(hitPoints, damage, 0, 0)
            }
        }
    }

    enum class Spell(var manaCost: Int) {
        MAGIC_MISSILE(53), DRAIN(73), SHIELD(113), POISON(173), RECHARGE(229)
    }

}