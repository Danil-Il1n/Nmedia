package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean = false,
    val likes: Int = 0,
    val sharedByMe: Boolean = false,
    val shared: Int = 0
)