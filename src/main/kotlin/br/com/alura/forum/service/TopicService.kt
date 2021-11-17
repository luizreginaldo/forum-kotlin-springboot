package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService {

    fun list(): List<Topic> {
        val topic = Topic(
            id = 1,
            title = "Kotlin doubt",
            message = "Variables in Kotlin",
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

        return listOf(topic, topic, topic)
    }

}