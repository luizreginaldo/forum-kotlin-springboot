package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(
    var courses: List<Course>
) {
    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programming"
        )

        courses = listOf(course)
    }

    fun list() {

    }

    fun findById(id: Long) : Course {
        return courses.stream().filter { course ->
            course.id == id
        }.findFirst().get()
    }

    fun create() {

    }
}
