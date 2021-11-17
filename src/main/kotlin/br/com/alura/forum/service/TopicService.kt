package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicDto
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<Topic> = mutableListOf(),
    private val courseService: CourseService,
    private val userService: UserService,
) {

    fun list(): List<Topic> {
        return topics
    }

    fun findById(id: Long): Topic {
        return topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()
    }

    fun create(newTopicDto: NewTopicDto) {
        topics.add(Topic(
            id = (topics.size + 1).toLong(),
            title = newTopicDto.title,
            message = newTopicDto.message,
            course = courseService.findById(newTopicDto.courseId),
            author = userService.findById(newTopicDto.authorId)
        ))
    }

}