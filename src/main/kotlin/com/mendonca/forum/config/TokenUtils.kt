package com.mendonca.forum.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.mendonca.forum.model.Usuario
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@Service
class TokenUtils() {

    @Value("\${api.security.token.secret}")
    private val secret: String? = null

    fun generateToken(usuario: Usuario): String {
        try {
            val algorithm: Algorithm = Algorithm.HMAC256(secret)

            return JWT.create()
                .withIssuer("VollMed")
                .withSubject(usuario.username)
                .withExpiresAt(dataExpiracao())
                .sign(algorithm)
        } catch (exception: JWTVerificationException) {
            throw RuntimeException("Erro ao gerar token")
        }
    }

    private fun dataExpiracao(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }

    fun getSubject(token: String?): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            return JWT.require(algorithm)
                .withIssuer("VollMed")
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            throw java.lang.RuntimeException("Token inválido ou expirado")
        }
    }

}