package com.example.srilankabarbershop.model

data class RegisterRequest(
    val nome:String,
    val email:String,
    val senha:String,
    val tipo: String = "CLIENTE"
)


