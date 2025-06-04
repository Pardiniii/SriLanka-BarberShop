package com.example.srilankabarbershop.model

data class AgendamentoRequest(
    val clienteId: Long,
    val servicoId: Long,
    val dataHora: String
)
