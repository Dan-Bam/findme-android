package com.example.domain.repository

import com.example.domain.entity.auth.TokenEntity
import com.example.domain.param.auth.CheckNumParam
import com.example.domain.param.auth.LoginParam
import com.example.domain.param.auth.SendNumParam
import com.example.domain.param.auth.SignUpParam

interface AuthRepository {
    suspend fun signUp(
        signUpParam: SignUpParam
    ): Void

    suspend fun login(
        loginParam: LoginParam
    ): TokenEntity

    suspend fun sendNum(
        sendNumParam: SendNumParam
    ): Void

    suspend fun checkNum(
        checkNumParam: CheckNumParam
    ): Void
}