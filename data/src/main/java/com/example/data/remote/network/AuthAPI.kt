package com.example.data.remote.network

import com.example.data.remote.request.auth.CheckNumRequest
import com.example.data.remote.request.auth.LoginRequest
import com.example.data.remote.request.auth.SendNumRequest
import com.example.data.remote.request.auth.SignUpRequest
import com.example.data.remote.response.auth.TokenResponse
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
    ): TokenResponse

    @POST("auth/send")
    suspend fun sendNum(
        @Body sendNumRequest: SendNumRequest
    ): Void

    @POST("auth/check")
    suspend fun checkNum(
        @Body checkNumRequest: CheckNumRequest
    ): Void
}