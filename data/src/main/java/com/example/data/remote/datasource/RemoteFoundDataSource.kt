package com.example.data.remote.datasource

import com.example.data.remote.request.found.FoundRequest

interface RemoteFoundDataSource {
    suspend fun registerFound(foundRequest: FoundRequest)
}