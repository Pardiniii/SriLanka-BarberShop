package com.example.srilankabarbershop.api

import com.example.srilankabarbershop.model.LoginRequest
import com.example.srilankabarbershop.model.LoginResponse
import com.example.srilankabarbershop.model.RecuperarSenhaRequest
import com.example.srilankabarbershop.model.RedefinirSenhaRequest
import com.example.srilankabarbershop.model.RegisterRequest
import com.example.srilankabarbershop.model.RegisterResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/auth/registrar")
    fun registrar(@Body request: RegisterRequest): Call<ResponseBody>

    @POST("/recuperar-senha")
    fun recuperarSenha(@Body request: RecuperarSenhaRequest): Call<String>

    @POST("/redefinir-senha")
    fun redefinirSenha(@Body request: RedefinirSenhaRequest): Call<String>


}
