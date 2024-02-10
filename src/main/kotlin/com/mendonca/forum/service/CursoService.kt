package com.mendonca.forum.service

import com.mendonca.forum.model.Curso
import com.mendonca.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {


    fun buscarPorId(id: Long): Curso {
        return repository.findById(id).orElseThrow { RuntimeException("Curso não encontrado") }
    }


}