package com.example.data.remote.datasource

import com.example.data.remote.network.AuthAPI
import com.example.data.remote.request.auth.CheckNumRequest
import com.example.data.remote.request.auth.LoginRequest
import com.example.data.remote.request.auth.SendNumRequest
import com.example.data.remote.request.auth.SignUpRequest
import com.example.data.remote.response.auth.TokenResponse
import com.example.data.util.HttpHandler
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
): RemoteAuthDataSource {
    override suspend fun signUp(signUpRequest: SignUpRequest): Void {
        return HttpHandler<Void>()
            .httpRequest { authAPI.signUp(signUpRequest = signUpRequest) }
            .sendRequest()
    }

    override suspend fun login(loginRequest: LoginRequest): TokenResponse {
        return HttpHandler<TokenResponse>()
            .httpRequest { authAPI.login(loginRequest = loginRequest) }
            .sendRequest()
    }

    override suspend fun sendNum(sendNumRequest: SendNumRequest): Void {
        return HttpHandler<Void>()
            .httpRequest { authAPI.sendNum(sendNumRequest = sendNumRequest) }
            .sendRequest()
    }

    override suspend fun checkNum(checkNumRequest: CheckNumRequest): Void {
        return HttpHandler<Void>()
            .httpRequest { authAPI.checkNum(checkNumRequest = checkNumRequest) }
            .sendRequest()
    }
}