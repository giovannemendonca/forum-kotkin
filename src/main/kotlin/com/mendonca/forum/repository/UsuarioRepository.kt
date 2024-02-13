package com.mendonca.forum.repository

import com.mendonca.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(username: String): UserDetails
}