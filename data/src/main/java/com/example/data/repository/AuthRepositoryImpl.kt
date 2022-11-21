package com.example.data.repository

import com.example.data.local.datasource.LocalAuthDataStore
import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.toEntity
import com.example.domain.entity.auth.TokenEntity
import com.example.domain.param.auth.CheckNumParam
import com.example.domain.param.auth.LoginParam
import com.example.domain.param.auth.SendNumParam
import com.example.domain.param.auth.SignUpParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: RemoteAuthDataSource,
    private val localAuthDataStore: LocalAuthDataStore
): AuthRepository {
    override suspend fun signUp(signUpParam: SignUpParam): Void {
        return authDataSource.signUp(signUpRequest = signUpParam.toRequest())
    }

    override suspend fun login(loginParam: LoginParam): TokenEntity {
        val response = authDataSource.login(loginRequest = loginParam.toRequest()).toEntity()
        localAuthDataStore.apply {
            setAccessToken(response.accessToken)
            setRefreshToken(response.refreshToken)
            setExpiredAt(response.expiredAt)
        }
        return response
    }

    override suspend fun sendNum(sendNumParam: SendNumParam): Void {
        return authDataSource.sendNum(sendNumRequest = sendNumParam.toRequest())
    }

    override suspend fun checkNum(checkNumParam: CheckNumParam): Void {
        return authDataSource.checkNum(checkNumRequest = checkNumParam.toRequest())
    }
}