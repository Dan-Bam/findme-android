package com.example.data.repository

import com.example.data.remote.datasource.AuthDataSource
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.toEntity
import com.example.domain.entity.LoginEntity
import com.example.domain.param.auth.LoginParam
import com.example.domain.param.auth.SignUpParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun signUp(signUpParam: SignUpParam): Void {
        return authDataSource.signUp(signUpRequest = signUpParam.toRequest())
    }

    override suspend fun login(loginParam: LoginParam): LoginEntity {
        return authDataSource.login(loginRequest = loginParam.toRequest()).toEntity()
    }
}