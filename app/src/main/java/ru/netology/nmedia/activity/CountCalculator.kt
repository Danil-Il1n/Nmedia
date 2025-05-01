package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import kotlin.math.floor

@SuppressLint("SetTextI18n", "DefaultLocale")
object CountCalculator {
    fun calculate(count: Int): String {

        val delMillion = 1_000_000.0
        val delThousand = 1_000.0
        val doubleСount = count.toDouble()
        return when {

            doubleСount >= 10_000_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 9_000_000.0 && doubleСount < 9_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 8_000_000.0 && doubleСount < 8_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 7_000_000.0 && doubleСount < 7_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 6_000_000.0 && doubleСount < 6_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 5_000_000.0 && doubleСount < 5_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 4_000_000.0 && doubleСount < 4_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 3_000_000.0 && doubleСount < 3_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 2_000_000.0 && doubleСount < 2_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 1_000_000.0 && doubleСount < 1_100_000.0 -> String.format("%.0fM", floor(doubleСount / delMillion))
            doubleСount >= 1_100_000.0 -> String.format("%.1fM", floor((doubleСount * 10) / delMillion) / 10)

            doubleСount >= 10_000.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 9_000.0 && doubleСount < 9_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 8_000.0 && doubleСount < 8_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 7_000.0 && doubleСount < 7_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 6_000.0 && doubleСount < 6_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 5_000.0 && doubleСount < 5_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 4_000.0 && doubleСount < 4_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 3_000.0 && doubleСount < 3_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 2_000.0 && doubleСount < 2_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 1_000.0 && doubleСount < 1_100.0 -> String.format("%.0fK", floor(doubleСount / delThousand))
            doubleСount >= 1_100.0 -> String.format("%.1fK", floor((doubleСount * 10) / delThousand) / 10)

            else -> String.format("%.0f", doubleСount)
        }
    }
}