package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class TopicService(
    private var topics: MutableList<Topic> = mutableListOf(),
    private val courseService: CourseService,
    private val userService: UserService,
) {

    fun list(): List<TopicView> {
        return topics.stream().map { topic ->
            TopicView(
                id = topic.id,
                title = topic.title,
                message = topic.message,
                createAt = topic.createdAt,
                status = topic.status
            )
        }.toList()
    }

    fun findById(id: Long): TopicView {
        val topic = topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()

        return TopicView(
            id = topic.id,
            title = topic.title,
            message = topic.message,
            createAt = topic.createdAt,
            status = topic.status
        )
    }

    fun create(topicForm: TopicForm) {
        topics.add(Topic(
            id = (topics.size + 1).toLong(),
            title = topicForm.title,
            message = topicForm.message,
            course = courseService.findById(topicForm.courseId),
            author = userService.findById(topicForm.authorId)
        ))
    }

}