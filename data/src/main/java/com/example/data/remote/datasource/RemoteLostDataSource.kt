package com.example.data.remote.datasource

import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse

interface RemoteLostDataSource {
    suspend fun registerLost(lostRequest: LostRequest)
    suspend fun editLost(lostId: String, lostRequest: LostRequest)
    suspend fun deleteLost(lostId: String)
    suspend fun findAll(): List<LostResponse>
}