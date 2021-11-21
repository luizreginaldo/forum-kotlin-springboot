package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicCreateForm
import br.com.alura.forum.dto.TopicUpdateForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.streams.toList

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found!"
) {

    fun list(courseName: String?, pagination: Pageable): Page<TopicView> {
        val topics = if(courseName == null) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(courseName, pagination)
        }
        return topics.map { topic ->
            topicViewMapper.map(topic)
        }
    }

    fun findById(id: Long): TopicView {
        val topic = repository.findById(id).orElseThrow{
            NotFoundException(notFoundMessage)
        }

        return topicViewMapper.map(topic)
    }

    fun create(form: TopicCreateForm) : TopicView {
        val topic = topicFormMapper.map(form)

        repository.save(topic)

        return topicViewMapper.map(topic)
    }

    fun update(form: TopicUpdateForm) : TopicView {
        val topic = repository.findById(form.id).orElseThrow{
            NotFoundException(notFoundMessage)
        }.apply {
            this.title = form.title
            this.message = form.message
        }

        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun report() : List<TopicByCategoryDto> {
        return repository.report()
    }

}