package com.example.domain.usecase.lost

import com.example.domain.param.lost.EditLostParam
import com.example.domain.repository.LostRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class EditLostUseCase @Inject constructor(
    private val lostRepository: LostRepository
) {
    suspend fun execute(lostId: String, editLostParam: EditLostParam, file: MultipartBody.Part?) =
        lostRepository.editLost(lostId, editLostParam, file)
}