package com.example.data.remote.datasource

import com.example.data.remote.request.found.EditFoundRequest
import com.example.data.remote.request.found.FoundRequest
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part

interface RemoteFoundDataSource {
    suspend fun registerFound(foundRequest: FoundRequest, file: Part)
    suspend fun editFound(foundId: String, editFoundRequest: EditFoundRequest, file: Part)
    suspend fun deleteFound(foundId: String)
}