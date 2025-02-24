package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.floor


class MainActivity : AppCompatActivity() {
    private var likeCount: Double = 0.0
    private var isLiked = false
    private var shareCount: Double = 0.0
    private var isShared = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left, systemBars.top, systemBars.right, systemBars.bottom
            )
            insets
        }

        val likeButton: ImageButton = findViewById(R.id.likeButton)
        val likeCountText: TextView = findViewById(R.id.likeCount)
        val shareButton: ImageButton = findViewById(R.id.shareButton)
        val shareCountText: TextView = findViewById(R.id.shareCount)

        val prefsLike = getSharedPreferences("prefsLike", MODE_PRIVATE)
        likeCount = prefsLike.getInt("like_count", 0).toDouble()
        isLiked = prefsLike.getBoolean("is_liked", false)

        likeCountText.text = likeCount.toString()
        setLikeState(likeButton, likeCountText, isLiked)

        likeButton.setOnClickListener {
            isLiked = !isLiked

            if (isLiked) {
                likeCount++
            } else {
                likeCount--
            }

            setLikeState(likeButton, likeCountText, isLiked)
            saveLikeState()
        }
        val prefsShare = getSharedPreferences("prefsShare", MODE_PRIVATE)
        shareCount = prefsShare.getInt("share_count", 2260).toDouble()
        isShared = prefsShare.getBoolean("is_shared", true)

        shareCountText.text = shareCount.toString()
        setShareState(shareCountText)

        shareButton.setOnClickListener {

            if (isShared) {
                shareCount++
            }

            setShareState(shareCountText)
            saveShareState()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setLikeState(button: ImageButton, textView: TextView, liked: Boolean) {
        val color = if (liked) R.color.red else R.color.gray
        button.setColorFilter(ContextCompat.getColor(this, color))
        textView.text = updateNum(likeCount)
    }

    private fun saveLikeState() {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        with(prefs.edit()) {
            putInt("like_count", likeCount.toInt()).putBoolean("is_liked", isLiked)
            apply()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setShareState(textView: TextView) {
        textView.text = updateNum(shareCount)
    }

    private fun saveShareState() {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        with(prefs.edit()) {
            putInt("share_count", shareCount.toInt())
            putBoolean("is_shared", isShared)
            apply()
        }
    }
}

@SuppressLint("SetTextI18n", "DefaultLocale")
fun updateNum(number: Double): String {
    val delMillion = 1_000_000.0
    val delThousand = 1_000.0
    return when {

        number >= 10_000_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 9_000_000.0 && number < 9_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 8_000_000.0 && number < 8_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 7_000_000.0 && number < 7_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 6_000_000.0 && number < 6_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 5_000_000.0 && number < 5_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 4_000_000.0 && number < 4_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 3_000_000.0 && number < 3_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 2_000_000.0 && number < 2_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 1_000_000.0 && number < 1_100_000.0 -> String.format("%.0fM", floor(number / delMillion))
        number >= 1_100_000.0 -> String.format("%.1fM", floor((number * 10) / delMillion) / 10)

        number >= 10_000.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 9_000.0 && number < 9_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 8_000.0 && number < 8_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 7_000.0 && number < 7_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 6_000.0 && number < 6_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 5_000.0 && number < 5_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 4_000.0 && number < 4_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 3_000.0 && number < 3_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 2_000.0 && number < 2_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 1_000.0 && number < 1_100.0 -> String.format("%.0fK", floor(number / delThousand))
        number >= 1_100.0 -> String.format("%.1fK", floor((number * 10) / delThousand) / 10)

        else -> String.format("%.0f", number)
    }
}