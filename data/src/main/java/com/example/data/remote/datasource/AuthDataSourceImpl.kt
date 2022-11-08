package com.example.data.remote.datasource

import com.example.data.remote.network.AuthAPI
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignUpRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.util.HttpHandler
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
): AuthDataSource {
    override suspend fun signUp(signUpRequest: SignUpRequest): Void {
        return HttpHandler<Void>()
            .httpRequest { authAPI.signUp(signUpRequest = signUpRequest) }
            .sendRequest()
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return HttpHandler<LoginResponse>()
            .httpRequest { authAPI.login(loginRequest = loginRequest) }
            .sendRequest()
    }
}