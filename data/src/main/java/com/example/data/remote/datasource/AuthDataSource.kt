package com.example.data.remote.datasource

import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignUpRequest
import com.example.data.remote.response.LoginResponse

interface AuthDataSource {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Void

    suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse
}