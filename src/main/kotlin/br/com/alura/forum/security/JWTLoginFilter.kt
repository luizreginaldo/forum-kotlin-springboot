package br.com.alura.forum.security

import br.com.alura.forum.config.JWTUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authManager: AuthenticationManager?,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

}
