package com.mendonca.forum.service

import com.mendonca.forum.model.Usuario
import com.mendonca.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).orElseThrow(
            { throw RuntimeException("Usuário não encontrado") }
        )
    }
}