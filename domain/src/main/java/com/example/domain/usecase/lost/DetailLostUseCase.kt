package com.example.domain.usecase.lost

import com.example.domain.repository.LostRepository
import javax.inject.Inject

class DetailLostUseCase @Inject constructor(
    private val lostRepository: LostRepository
) {
    suspend fun execute(lostId: String) =
        lostRepository.detailLost(lostId)
}