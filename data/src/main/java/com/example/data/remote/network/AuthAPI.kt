package com.example.data.remote.network

import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignUpRequest
import com.example.data.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Void

    @POST("auth/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}