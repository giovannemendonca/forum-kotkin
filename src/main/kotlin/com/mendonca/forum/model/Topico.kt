package com.mendonca.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "TOPICO")
data class Topico (

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "TITULO")
    var titulo: String,

    @Column(name = "MENSAGEM")
    var mensagem: String,

    @Column(name = "DATA_CRIACAO")
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "CURSO_ID")
    val curso: Curso,

    @ManyToOne
    @JoinColumn(name = "AUTOR_ID")
    val autor: Usuario,

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @Column(name = "RESPOSTAS")
    @OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList()
)