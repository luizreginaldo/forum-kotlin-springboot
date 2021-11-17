package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: List<Topic> = listOf()
) {

    fun list(): List<Topic> {
        return topics
    }

    fun findById(id: Long): Topic {
        return topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
    }

    fun create(topic: Topic) {
        topics.plus(topic)
    }

}