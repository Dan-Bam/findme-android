package com.example.data.remote.datasource

import com.example.data.remote.request.lost.EditLostRequest
import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part
import okhttp3.RequestBody

interface RemoteLostDataSource {
    suspend fun registerLost(lostRequest: LostRequest, file: Part)
    suspend fun editLost(lostId: String, editLostRequest: EditLostRequest, file: Part?)
    suspend fun deleteLost(lostId: String)
    suspend fun detailLost(lostId: String): LostResponse
    suspend fun findAll(): List<LostResponse>
    suspend fun findCategory(category: String?, address: String): List<LostResponse>
}