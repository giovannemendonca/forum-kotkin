package com.mendonca.forum.service

import com.mendonca.forum.dto.AtualizacaoTopicoForm
import com.mendonca.forum.dto.NovoTopicoForm
import com.mendonca.forum.dto.TopicoPorCategoriaDto
import com.mendonca.forum.dto.TopicoView
import com.mendonca.forum.exception.NotFouldException
import com.mendonca.forum.mapper.TopicoFormMapper
import com.mendonca.forum.mapper.TopicoViewMapper
import com.mendonca.forum.repository.TopicoRepository
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {


    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        
        val topicos = if (nomeCurso == null) {
            topicoRepository.findAll(paginacao)
        } else {
            topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { topico -> topicoViewMapper.map(topico) }
    }

    fun buscar(id: Long): TopicoView {

        val topico = topicoRepository.findById(id)
            .orElseThrow { NotFouldException(notFoundMessage) }
        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        val topicoSaved = topicoRepository.save(topico)

        return topicoViewMapper.map(topicoSaved)
    }

    @Transactional
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicoRepository.findById(form.id)
            .orElseThrow { NotFouldException(notFoundMessage) }

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem

        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun deletar(id: Long) {
        topicoRepository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoRepository.relatorio()
    }

}