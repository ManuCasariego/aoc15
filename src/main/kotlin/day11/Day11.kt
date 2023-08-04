package day11

import Day

class Day11 : Day(11) {
    override fun part1(): Int {
        TODO("Not yet implemented")
    }

    fun part1String(): String {
        val password = Password.builder(inputString)
        while (!validPassword(password)) {
            password.add()
        }
        return password.toString()
    }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }

    fun part2String(): String {
        val password = Password.builder(inputString)
        while (!validPassword(password)) {
            password.add()
        }
        password.add()
        while (!validPassword(password)) {
            password.add()
        }
        return password.toString()
    }

    private fun validPassword(password: Password): Boolean {
        //Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
        var condition = false
        for (i in 0..5) {
            if (password.password[i] + 1 == password.password[i + 1] && password.password[i] + 2 == password.password[i + 2]
            ) condition = true
        }
        if (!condition) return false

        //Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
        if (password.password.contains(Password.alphabet.indexOf('i')) ||
            password.password.contains(Password.alphabet.indexOf('o')) ||
            password.password.contains(Password.alphabet.indexOf('l'))
        ) return false

        //Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
        var pairOfLeters = mutableSetOf<Char>()
        for (i in 0..6) {
            if (password.password[i] == password.password[i + 1]) pairOfLeters.add(
                Password.alphabet.elementAt(
                    password.password[i]
                )
            )
        }
        return (pairOfLeters.size >= 2)
    }

    data class Password(val password: Array<Int>) {
        override fun toString(): String {
            val stringBuilder = StringBuilder()
            for (pwdChar in password) {
                stringBuilder.append(alphabet[pwdChar])
            }
            return stringBuilder.toString()
        }

        fun add() {
            for (i in 7 downTo 0) {
                if (password[i] + 1 < alphabet.size) {
                    password[i]++
                    return
                } else {
                    password[i] = 0
                }
            }
            print("we got to the end - 00000000")
        }

        companion object {
            val alphabet = listOf(
                'a',
                'b',
                'c',
                'd',
                'e',
                'f',
                'g',
                'h',
                'i',
                'j',
                'k',
                'l',
                'm',
                'n',
                'o',
                'p',
                'q',
                'r',
                's',
                't',
                'u',
                'v',
                'w',
                'x',
                'y',
                'z'
            )

            fun builder(string: String): Password {
                val array = Array(8) { 0 }
                for (i in 0..7) {
                    array[i] = alphabet.indexOf(string[i])
                }
                return Password(array)
            }
        }
    }
}