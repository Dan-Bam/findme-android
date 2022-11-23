package com.example.data.remote.datasource

import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse

interface RemoteLostDataSource {
    suspend fun registerLost(lostRequest: LostRequest)
    suspend fun findAll(): List<LostResponse>
}