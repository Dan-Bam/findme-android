package com.example.domain.usecase.found

import com.example.domain.repository.FoundRepository
import javax.inject.Inject

class DeleteFoundUseCase @Inject constructor(
    private val foundRepository: FoundRepository
) {
    suspend fun execute(foundId: String) =
        foundRepository.deleteFound(foundId)
}