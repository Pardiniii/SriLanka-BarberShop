package com.example.srilankabarbershop.api

import com.example.srilankabarbershop.model.LoginRequest
import com.example.srilankabarbershop.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}
