package com.mendonca.forum.config

import com.mendonca.forum.repository.UsuarioRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenUtils: TokenUtils,
    private val usuarioRepository: UsuarioRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val tokenJWT = recuperarToken(request)

        if (tokenJWT != null) {
            val subject = tokenUtils.getSubject(tokenJWT)
            val usuario = usuarioRepository.findByEmail(subject)
            val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())

            SecurityContextHolder.getContext().authentication = authentication
        }


        filterChain.doFilter(request, response)
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "")
        }
        return null
    }
}