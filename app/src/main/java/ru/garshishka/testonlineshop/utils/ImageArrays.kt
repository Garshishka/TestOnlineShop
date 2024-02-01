package ru.garshishka.testonlineshop.utils

import ru.garshishka.testonlineshop.R

fun getImageArrayForItem(id: String): IntArray {
    return when (id) {
        "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> intArrayOf(
            R.drawable.product_6,
            R.drawable.product_5
        )

        "54a876a5-2205-48ba-9498-cfecff4baa6e" -> intArrayOf(
            R.drawable.product_1,
            R.drawable.product_2
        )

        "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> intArrayOf(
            R.drawable.product_5,
            R.drawable.product_6
        )

        "16f88865-ae74-4b7c-9d85-b68334bb97db" -> intArrayOf(
            R.drawable.product_3,
            R.drawable.product_4
        )

        "26f88856-ae74-4b7c-9d85-b68334bb97db" -> intArrayOf(
            R.drawable.product_2,
            R.drawable.product_3
        )

        "15f88865-ae74-4b7c-9d81-b78334bb97db" -> intArrayOf(
            R.drawable.product_6,
            R.drawable.product_1
        )

        "88f88865-ae74-4b7c-9d81-b78334bb97db" -> intArrayOf(
            R.drawable.product_4,
            R.drawable.product_3
        )

        "55f58865-ae74-4b7c-9d81-b78334bb97db" -> intArrayOf(
            R.drawable.product_1,
            R.drawable.product_5
        )

        else -> intArrayOf(0, 0)
    }
}