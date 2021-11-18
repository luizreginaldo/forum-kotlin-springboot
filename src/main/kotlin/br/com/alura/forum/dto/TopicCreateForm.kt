package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicCreateForm(
    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val title: String,

    @field:NotEmpty
    @field:Size(min = 5)
    val message: String,

    @field:NotNull
    val courseId: Long,

    @NotNull
    val authorId: Long,
)
