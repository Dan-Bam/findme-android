package com.example.data.remote.datasource

import com.example.data.remote.response.lost.FindAllResponse

interface RemoteLostDataSource {
    suspend fun findAll(): List<FindAllResponse>
}