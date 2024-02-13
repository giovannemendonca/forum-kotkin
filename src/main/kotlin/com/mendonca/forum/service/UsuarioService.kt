package com.mendonca.forum.service

import com.mendonca.forum.model.Usuario
import com.mendonca.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return usuarioRepository.findByEmail(username!!)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarioRepository.findById(id).orElseThrow(
            { throw RuntimeException("Usuário não encontrado") }
        )
    }
}