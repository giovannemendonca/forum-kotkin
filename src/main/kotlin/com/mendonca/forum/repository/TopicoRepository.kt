package com.mendonca.forum.repository

import com.mendonca.forum.dto.TopicoPorCategoriaDto
import com.mendonca.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long>{

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    @Query("SELECT new com.mendonca.forum.dto.TopicoPorCategoriaDto(curso.categoria, COUNT(T)) FROM TOPICO T " +
            "JOIN T.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDto>
}