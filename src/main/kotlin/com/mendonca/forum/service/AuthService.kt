package com.mendonca.forum.service

import com.mendonca.forum.config.TokenUtils
import com.mendonca.forum.dto.LoginDTO
import com.mendonca.forum.dto.TokenDto
import com.mendonca.forum.model.Usuario
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val manager: AuthenticationManager,
    private val tokenUtils: TokenUtils
) {

    fun login(loginDTO: LoginDTO): TokenDto {

        val authenticationToken = UsernamePasswordAuthenticationToken(loginDTO.email, loginDTO.password)
        val authenticate = manager.authenticate(authenticationToken)
        val token = tokenUtils.generateToken(authenticate.principal as Usuario)

        return TokenDto(token);
    }

}