package com.example.domain.usecase.lost

import com.example.domain.param.lost.LostParam
import com.example.domain.repository.LostRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class EntryLostUseCase @Inject constructor(
    private val lostRepository: LostRepository
) {
    suspend fun execute(lostParam: LostParam, file: MultipartBody.Part) =
        lostRepository.registerLost(lostParam, file)
}