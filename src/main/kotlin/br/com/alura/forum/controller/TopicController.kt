package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicCreateForm
import br.com.alura.forum.dto.TopicUpdateForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.service.TopicService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(
    private val service: TopicService
) {

    @GetMapping
    fun list() : List<TopicView> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun finById(@PathVariable id: Long) : TopicView {
        return service.findById(id)
    }

    @PostMapping
    fun create(@RequestBody @Valid form: TopicCreateForm) {
        service.create(form)
    }

    @PutMapping
    fun update(@RequestBody @Valid form: TopicUpdateForm) {
        service.update(form)
    }

}