package br.com.alura.forum.service

import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    var users: List<User>
) {

    init {
        val user = User(
            id = 1,
            name = "Luiz Reginaldo",
            email = "contato@luizreginaldo.com"
        )

        users = listOf(user)
    }

    fun list() {

    }

    fun findById(id: Long) : User {
        return users.stream().filter { user ->
            user.id == id
        }.findFirst().get()
    }

    fun create() {
        
    }
}
