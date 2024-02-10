package com.mendonca.forum.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class AtualizacaoTopicoForm (

    @field:NotNull
    val id: Long,

    @field:NotBlank
    @field:Size(min = 5, max = 100)
    val titulo: String,

    @field:NotBlank
    val mensagem: String
)