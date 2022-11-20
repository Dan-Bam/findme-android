package com.example.domain.usecase.auth

import com.example.domain.param.auth.CheckNumParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class CheckNumUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(checkNumParam: CheckNumParam) =
        authRepository.checkNum(checkNumParam = checkNumParam)
}