package ru.netology.nmedia.view

import android.annotation.SuppressLint
import kotlin.math.floor

@SuppressLint("SetTextI18n", "DefaultLocale")
object CountCalculator {
    fun calculate(count: Double): String {

        val delMillion = 1_000_000.0
        val delThousand = 1_000.0
        return when {

            count >= 10_000_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 9_000_000.0 && count < 9_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 8_000_000.0 && count < 8_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 7_000_000.0 && count < 7_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 6_000_000.0 && count < 6_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 5_000_000.0 && count < 5_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 4_000_000.0 && count < 4_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 3_000_000.0 && count < 3_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 2_000_000.0 && count < 2_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 1_000_000.0 && count < 1_100_000.0 -> String.format("%.0fM", floor(count / delMillion))
            count >= 1_100_000.0 -> String.format("%.1fM", floor((count * 10) / delMillion) / 10)

            count >= 10_000.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 9_000.0 && count < 9_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 8_000.0 && count < 8_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 7_000.0 && count < 7_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 6_000.0 && count < 6_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 5_000.0 && count < 5_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 4_000.0 && count < 4_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 3_000.0 && count < 3_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 2_000.0 && count < 2_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 1_000.0 && count < 1_100.0 -> String.format("%.0fK", floor(count / delThousand))
            count >= 1_100.0 -> String.format("%.1fK", floor((count * 10) / delThousand) / 10)

            else -> String.format("%.0f", count)
        }
    }
}