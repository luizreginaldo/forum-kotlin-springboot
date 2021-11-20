package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val course: Course,

    @ManyToOne
    val author: User,

    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus = TopicStatus.UNANSWERED,

    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = ArrayList()
)