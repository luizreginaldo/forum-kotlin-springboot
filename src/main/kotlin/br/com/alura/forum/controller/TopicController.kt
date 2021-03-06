package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicCreateForm
import br.com.alura.forum.dto.TopicUpdateForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
    @Cacheable("topicList")
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pagination : Pageable
    ) : Page<TopicView> {
        return service.list(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun finById(@PathVariable id: Long) : TopicView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicList"], allEntries = true)
    fun create(
        @RequestBody @Valid form: TopicCreateForm,
        uriComponentsBuilder: UriComponentsBuilder
    ) : ResponseEntity<TopicView> {
        val topicView = service.create(form)
        val uri = uriComponentsBuilder.path("/topics/${topicView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicList"], allEntries = true)
    fun update(@RequestBody @Valid form: TopicUpdateForm) : ResponseEntity<TopicView> {
        return ResponseEntity.ok(service.update(form))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topicList"], allEntries = true)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/report")
    fun report(): List<TopicByCategoryDto> {
        return service.report()
    }

}