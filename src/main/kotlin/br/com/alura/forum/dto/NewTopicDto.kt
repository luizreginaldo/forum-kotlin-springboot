package br.com.alura.forum.dto

data class NewTopicDto(
    val title: String,
    val message: String,
    val courseId: Long,
    val authorId: Long,
)
