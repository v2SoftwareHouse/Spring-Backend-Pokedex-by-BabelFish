package com.v2.sample.babelfish


import java.time.LocalDate
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class DataFactory {
    companion object Factory {

        fun randomUuid() = UUID.randomUUID().toString()

        private fun randomInt() = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

        private fun randomDouble() = randomInt().toDouble()

        private fun randomLong() = randomInt().toLong()

        private fun randomDate() = LocalDate.ofEpochDay(randomLong())

        private fun randomBoolean() = Math.random() < 0.5

        fun mockPokemon() = randomUuid()

        fun mockPokemonList() = (0..10).map { mockPokemon() }.toMutableList()
    }
}