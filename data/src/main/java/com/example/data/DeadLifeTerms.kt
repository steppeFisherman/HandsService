package com.example.data

import kotlin.random.Random

interface DeadLifeTerms {

    fun filter(list: MutableList<Item>): List<Item>

    class ThreeByThree : DeadLifeTerms {

        private val predicateDead: (Item) -> Boolean = { it.name == "Мёртвая" }
        private val predicateAlive: (Item) -> Boolean = { it.name == "Живая" }

        override fun filter(list: MutableList<Item>): List<Item> {

            val originList = listOf(Item("Мёртвая"), Item("Живая"))
            val randomIndex = Random.nextInt(originList.size)
            val randomElement = originList[randomIndex]
            list.add(randomElement)

            if (list.size >= 3) {
                val lastThreeElements = list.takeLast(3)
                val lastThreeAlive = lastThreeElements.all(predicateAlive)
                val lastThreeDead = lastThreeElements.all(predicateDead)

                if (lastThreeAlive) list.add(Item("Жизнь"))
                if (lastThreeDead) list.remove(Item("Жизнь"))
            }
            return list
        }
    }
}

