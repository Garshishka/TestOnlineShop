package ru.garshishka.testonlineshop.utils

fun getWordForAmount(number: Int, word: String): String {
    val lastDigit = number % 10
    val exceptions = setOf(11, 12, 13, 14)

    return when {
        number.toString().length>1 && number%100 in exceptions -> "$number ${word}ов"
        lastDigit == 1  -> "$number $word"
        lastDigit in 2..4 -> "$number ${word}а"
        else -> "$number ${word}ов"
    }
}

fun getWordForAvailable(number: Int): String {
    val lastDigit = number % 10
    val exceptions = setOf(11, 12, 13, 14)

    return when {
        number.toString().length>1 && number%100 in exceptions -> "$number штук"
        lastDigit == 1  -> "$number штука"
        lastDigit in 2..4 -> "$number штуки"
        else -> "$number штук"
    }
}