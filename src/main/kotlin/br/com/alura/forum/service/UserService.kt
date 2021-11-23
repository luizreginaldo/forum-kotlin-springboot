package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.User
import br.com.alura.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) : UserDetailsService {

    fun findById(id: Long) : User {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username) ?: throw NotFoundException("user not found")

        return UserDetail(user)
    }
}
