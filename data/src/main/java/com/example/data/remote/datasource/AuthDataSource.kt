package com.example.data.remote.datasource

import com.example.data.remote.request.SignUpRequest

interface AuthDataSource {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Void
}