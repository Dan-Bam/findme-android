package com.example.data.remote.datasource

import com.example.data.remote.request.auth.CheckNumRequest
import com.example.data.remote.request.auth.LoginRequest
import com.example.data.remote.request.auth.SendNumRequest
import com.example.data.remote.request.auth.SignUpRequest
import com.example.data.remote.response.auth.TokenResponse

interface RemoteAuthDataSource {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Void

    suspend fun login(
        loginRequest: LoginRequest
    ): TokenResponse

    suspend fun sendNum(
        sendNumRequest: SendNumRequest
    ): Void

    suspend fun checkNum(
        checkNumRequest: CheckNumRequest
    ): Void
}