package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topic(
    var id: Long? = null,
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: TopicStatus = TopicStatus.UNANSWERED,
    val answers: List<Answer> = ArrayList()
)