package ru.netology.nmedia.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val navBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            val basePadding = resources.getDimensionPixelSize(R.dimen.edit_text_base_padding)
            val bottomPadding = maxOf(imeInsets.bottom, navBarInsets.bottom) + basePadding
            val topPadding = navBarInsets.top

            binding.content.updatePadding(bottom = bottomPadding)

            val saveButtonOffset = resources.getDimensionPixelSize(R.dimen.save_button_offset)
            binding.save.updatePadding(bottom = bottomPadding - saveButtonOffset)

            binding.list.updatePadding(top = topPadding)

            insets
        }

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id != 0L) {
                with(binding.content) {
                    setText(post.content)
                    requestFocus()
                    AndroidUtils.showKeyboard(this)
                }
                binding.cancel.visibility = android.view.View.VISIBLE
                binding.cancelText.visibility = android.view.View.VISIBLE
            } else {
                binding.content.setText("")
                binding.content.clearFocus()
                binding.cancel.visibility = android.view.View.GONE
                binding.cancelText.visibility = android.view.View.GONE
            }
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                val text = text.toString()
                if (text.isBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text)
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        binding.cancel.setOnClickListener {
            viewModel.cancelEdit()
            AndroidUtils.hideKeyboard(binding.content)
        }

        binding.cancelText.setOnClickListener {
            viewModel.cancelEdit()
            AndroidUtils.hideKeyboard(binding.content)
        }
    }
}