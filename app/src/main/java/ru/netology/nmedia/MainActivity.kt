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


class MainActivity : AppCompatActivity() {
    private var likeCount: Double = 999.0
    private var isLiked = false
    private var shareCount: Double = 1_099_999.0
    private var isShared = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
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
        shareCount = prefsShare.getInt("share_count", 0).toDouble()
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
            putInt("like_count", likeCount.toInt()).
            putBoolean("is_liked", isLiked)
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
    return when {
        number >= 1_000_000_000.0 && number < 1_100_000_000.0 -> String.format("%.0fB", number / 1_000_000_000.0)
        number >= 1_100_000_000.0 -> String.format("%.1fB", number / 1_000_000_000.0)

        number >= 1_000_000.0 && number < 1_100_000.0 -> String.format("%.0fM", number / 1_000_000.0)
        number >= 1_100_000.0 -> String.format("%.1fM", number / 1_000_000.0)

        number >= 1_000.0 && number < 1_100.0 -> String.format("%.0fK", number / 1_000.0)
        number >= 1_100.0 -> String.format("%.1fK", number / 1_000.0)

        else -> String.format("%.0f", number)
    }
}