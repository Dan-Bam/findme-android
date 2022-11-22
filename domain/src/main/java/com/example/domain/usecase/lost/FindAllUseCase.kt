package com.example.domain.usecase.lost

import com.example.domain.repository.LostRepository
import javax.inject.Inject

class FindAllUseCase @Inject constructor(
    private val lostRepository: LostRepository
) {
    suspend fun execute() =
        lostRepository.findAll()
}