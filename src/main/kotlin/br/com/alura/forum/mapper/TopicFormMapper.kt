package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicCreateForm
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService,
) : Mapper<TopicCreateForm, Topic> {

    override fun map(t: TopicCreateForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.findById(t.courseId),
            author = userService.findById(t.authorId)
        )
    }

}
