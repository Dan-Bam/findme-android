package com.example.domain.usecase.found

import com.example.domain.param.found.FoundParam
import com.example.domain.repository.FoundRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class EntryFoundUseCase @Inject constructor(
    private val foundRepository: FoundRepository
) {
    suspend fun execute(foundParam: FoundParam, file: MultipartBody.Part) =
        foundRepository.registerFound(foundParam, file)
}