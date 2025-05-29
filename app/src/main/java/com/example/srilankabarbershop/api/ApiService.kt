package com.example.srilankabarbershop.api

import com.example.srilankabarbershop.model.LoginRequest
import com.example.srilankabarbershop.model.LoginResponse
import com.example.srilankabarbershop.model.RegisterRequest
import com.example.srilankabarbershop.model.RegisterResponse
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
    fun registrar(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/recuperar-senha")
    fun recuperarSenha(@Body request: RecuperarSenhaRequest): Call<String>

    @POST("/redefinir-senha")
    fun redefinirSenha(@Body request: RedefinirSenhaRequest): Call<String>

    @POST("/estabelecimentos")
    fun criarEstabelecimento(
        @Body request: EstabelecimentoRequest,
        @Header("Authorization") token: String
    ): Call<EstabelecimentoResponse>

    @GET("/estabelecimentos")
    fun listarEstabelecimentos(
        @Header("Authorization") token: String
    ): Call<List<EstabelecimentoResponse>>

    @PUT("/estabelecimentos/{id}")
    fun atualizarEstabelecimento(
        @Path("id") id: Long,
        @Body request: EstabelecimentoRequest,
        @Header("Authorization") token: String
    ): Call<String>

    @DELETE("/estabelecimentos/{id}")
    fun deletarEstabelecimento(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Call<String>

    @GET("/estabelecimentos/buscar-por-cep/{cep}")
    fun buscarPorCep(
        @Path("cep") cep: String
    ): Call<List<EstabelecimentoResponse>>

}
