package com.example.srilankabarbershop.model

data class RedefinirSenhaRequest(
    val email: String,
    val codigo: String,
    val novaSenha: String
)
