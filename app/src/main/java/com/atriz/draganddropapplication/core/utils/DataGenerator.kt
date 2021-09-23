package com.atriz.draganddropapplication.core.utils

import com.atriz.draganddropapplication.core.data.BankCard
import kotlin.random.Random

object DataGenerator {

    fun generateRandomList(size: Int = 10): List<BankCard> {
        val list = arrayListOf<BankCard>()
        for(i in 1..size) {
            list.add(generateBankCard())
        }
        return list
    }

    private fun generateBankCard(): BankCard {
        val number = Random.nextInt(1000, 9999).toString() + " " +
                Random.nextInt(1000, 9999).toString() + " " +
                Random.nextInt(1000, 9999).toString() + " " +
                Random.nextInt(1000, 9999).toString()

        val date = Random.nextInt(10, 31).toString() + "/" +
                Random.nextInt(1, 12).toString()

        val cvv = Random.nextInt(100, 999).toString()

        return BankCard(number, date, cvv)
    }
}