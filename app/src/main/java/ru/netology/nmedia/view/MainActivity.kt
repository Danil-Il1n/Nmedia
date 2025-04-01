package ru.netology.nmedia.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content


                val color = if (post.likedByMe) R.color.red else R.color.gray
                like.setColorFilter(getColor(color))
                likes.text = CountCalculator.calculate(post.likes)
                shared.text = CountCalculator.calculate(post.shared)
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.shareButton.setOnClickListener{
            viewModel.share()
        }
    }
}