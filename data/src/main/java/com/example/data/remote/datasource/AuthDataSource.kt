package com.example.data.remote.datasource

import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SendNumRequest
import com.example.data.remote.request.SignUpRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.RefreshResponse

interface AuthDataSource {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Void

    suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse

    suspend fun refresh(): RefreshResponse

    suspend fun sendNum(
        sendNumRequest: SendNumRequest
    ): Void
}