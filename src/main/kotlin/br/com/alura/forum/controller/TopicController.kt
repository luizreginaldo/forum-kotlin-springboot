package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicCreateForm
import br.com.alura.forum.dto.TopicUpdateForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(
    private val service: TopicService
) {

    @GetMapping
    fun list(@RequestParam(required = false) courseName: String?) : List<TopicView> {
        return service.list(courseName)
    }

    @GetMapping("/{id}")
    fun finById(@PathVariable id: Long) : TopicView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid form: TopicCreateForm,
        uriComponentsBuilder: UriComponentsBuilder
    ) : ResponseEntity<TopicView> {
        val topicView = service.create(form)
        val uri = uriComponentsBuilder.path("/topicos/${topicView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid form: TopicUpdateForm) : ResponseEntity<TopicView> {
        return ResponseEntity.ok(service.update(form))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}