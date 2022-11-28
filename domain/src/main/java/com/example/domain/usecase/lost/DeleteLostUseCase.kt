package com.example.domain.usecase.lost

import com.example.domain.repository.LostRepository
import javax.inject.Inject

class DeleteLostUseCase @Inject constructor(
    private val lostRepository: LostRepository
) {
    suspend fun execute(lostId: String) =
        lostRepository.deleteLost(lostId)
}