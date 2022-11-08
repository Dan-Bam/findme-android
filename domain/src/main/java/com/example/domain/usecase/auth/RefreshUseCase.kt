package com.example.domain.usecase.auth

import com.example.domain.entity.RefreshEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class RefreshUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(): RefreshEntity =
        authRepository.refresh()
}