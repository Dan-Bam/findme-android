package com.example.domain.usecase.found

import com.example.domain.param.found.EditFoundParam
import com.example.domain.repository.FoundRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class EditFoundUseCase @Inject constructor(
    private val foundRepository: FoundRepository
) {
    suspend fun execute(foundId: String, editFoundParam: EditFoundParam, file: MultipartBody.Part?) =
        foundRepository.editFound(foundId, editFoundParam, file)
}