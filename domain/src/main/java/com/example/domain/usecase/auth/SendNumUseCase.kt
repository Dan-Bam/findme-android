package com.example.domain.usecase.auth

import com.example.domain.param.auth.SendNumParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SendNumUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(sendNumParam: SendNumParam) =
        authRepository.sendNum(sendNumParam = sendNumParam)
}