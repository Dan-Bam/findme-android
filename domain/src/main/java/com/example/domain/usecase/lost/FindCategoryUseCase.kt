package com.example.domain.usecase.lost

import com.example.domain.repository.LostRepository
import javax.inject.Inject

class FindCategoryUseCase @Inject constructor(
    private val lostRepository: LostRepository
) {
    suspend fun execute(category: String?, address: String) =
        lostRepository.findCategory(category, address)
}