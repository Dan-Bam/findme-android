package com.example.data.remote.datasource

import com.example.data.remote.response.lost.LostResponse

interface RemoteLostDataSource {
    suspend fun findAll(): List<LostResponse>
}