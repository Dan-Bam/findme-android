package com.example.domain.usecase.user

import com.example.domain.repository.FoundRepository
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class RecommendFoundUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute() =
        userRepository.recommendFound()
}