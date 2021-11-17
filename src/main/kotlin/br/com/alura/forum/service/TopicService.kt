package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: List<Topic>
) {

    init {
        val topic = Topic(
            id = 1,
            title = "Kotlin doubt 1",
            message = "Variables in Kotlin 1",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programming"
            ),
            author = User(
                id = 1,
                name = "Luiz Reginaldo",
                email = "contato@luizreginaldo.com"
            )
        )

        val topic2 = Topic(
            id = 2,
            title = "Kotlin doubt 2",
            message = "Variables in Kotlin 2",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programming"
            ),
            author = User(
                id = 1,
                name = "Luiz Reginaldo",
                email = "contato@luizreginaldo.com"
            )
        )

        val topic3 = Topic(
            id = 3,
            title = "Kotlin doubt 3",
            message = "Variables in Kotlin 3",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programming"
            ),
            author = User(
                id = 1,
                name = "Luiz Reginaldo",
                email = "contato@luizreginaldo.com"
            )
        )

        topics = listOf(topic, topic2, topic3)
    }

    fun list(): List<Topic> {
        return topics
    }

    fun findById(id: Long): Topic {
        return topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
    }

}