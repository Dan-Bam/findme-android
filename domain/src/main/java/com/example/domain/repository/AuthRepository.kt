package com.example.domain.repository

import com.example.domain.entity.LoginEntity
import com.example.domain.param.auth.LoginParam
import com.example.domain.param.auth.SignUpParam

interface AuthRepository {
    suspend fun signUp(
        signUpParam: SignUpParam
    ): Void

    suspend fun login(
        loginParam: LoginParam
    ): LoginEntity
}