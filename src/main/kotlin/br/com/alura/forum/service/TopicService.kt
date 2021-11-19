package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicCreateForm
import br.com.alura.forum.dto.TopicUpdateForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class TopicService(
    private var topics: MutableList<Topic> = mutableListOf(),
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
) {

    fun list(): List<TopicView> {
        return topics.stream().map { topic ->
            topicViewMapper.map(topic)
        }.toList()
    }

    fun findById(id: Long): TopicView {
        val topic = topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get()

        return topicViewMapper.map(topic)
    }

    fun create(form: TopicCreateForm) : TopicView {
        val topic = topicFormMapper.map(form).apply {
            this.id = (topics.size + 1).toLong()
        }

        topics.add(topic)

        return topicViewMapper.map(topic)
    }

    fun update(form: TopicUpdateForm) : TopicView {
        val topic = topics.stream().filter { topic ->
            topic.id == form.id
        }.findFirst().get().apply {
            this.title = form.title
            this.message = form.message
        }

        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        topics.remove(topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().get())
    }

}