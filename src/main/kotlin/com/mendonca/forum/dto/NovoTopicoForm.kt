package com.mendonca.forum.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoTopicoForm (

    @field:NotBlank
    @field:Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres")
    val titulo: String,

    @field:NotBlank
    val mensagem: String,

    @field:NotNull
    val idCurso: Long,

    @field:NotNull
    val idAutor: Long
)