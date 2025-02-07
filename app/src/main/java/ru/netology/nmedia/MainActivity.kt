package ru.netology.nmedia

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var likeCount = 0
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val likeButton: ImageButton = findViewById(R.id.likeButton)
        val likeCountText: TextView = findViewById(R.id.likeCount)

        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        likeCount = prefs.getInt("like_count", 0)
        isLiked = prefs.getBoolean("is_liked", false)

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
    }

    private fun setLikeState(button: ImageButton, textView: TextView, liked: Boolean) {
        val color = if (liked) R.color.red else R.color.gray
        button.setColorFilter(ContextCompat.getColor(this, color))
        textView.text = likeCount.toString()
    }

    private fun saveLikeState() {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        with(prefs.edit()) {
            putInt("like_count", likeCount)
            putBoolean("is_liked", isLiked)
            apply()
        }
    }
}