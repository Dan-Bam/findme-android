package com.example.data.remote.network

import com.example.data.remote.request.CheckNumRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SendNumRequest
import com.example.data.remote.request.SignUpRequest
import com.example.data.remote.response.TokenResponse
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