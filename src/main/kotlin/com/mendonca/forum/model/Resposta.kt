package com.mendonca.forum.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Resposta (

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "DATA_CRIACAO")
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "USUARIO")
    val autor: Usuario,

    @ManyToOne
    @JoinColumn(name = "TOPICO")
    val topico: Topico,

    @Column(name = "SOLUCAO")
    val solucao: Boolean,

    @Column(name = "MENSAGEM")
    val mensagem: String
)
