package com.mendonca.forum.controller

import com.mendonca.forum.dto.LoginDTO
import com.mendonca.forum.dto.TokenDto
import com.mendonca.forum.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping
    fun login(@RequestBody @Valid loginDTO: LoginDTO): TokenDto {
        return authService.login(loginDTO)
    }

}