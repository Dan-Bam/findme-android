package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class MyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute() =
        userRepository.myInfo()
}