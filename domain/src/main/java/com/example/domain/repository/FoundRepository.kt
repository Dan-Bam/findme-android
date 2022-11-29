package com.example.domain.repository

import com.example.domain.entity.found.FoundEntity
import com.example.domain.param.found.EditFoundParam
import com.example.domain.param.found.FoundParam
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part

interface FoundRepository {
    suspend fun registerFound(foundParam: FoundParam, file: Part)
    suspend fun editFound(foundId: String, editFoundParam: EditFoundParam, file: Part)
    suspend fun deleteFound(foundId: String)
    suspend fun detailFound(foundId: String): FoundEntity
}