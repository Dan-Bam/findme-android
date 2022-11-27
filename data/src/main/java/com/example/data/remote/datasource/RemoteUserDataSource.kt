package com.example.data.remote.datasource

import com.example.data.remote.response.user.MyEntryResponse

interface RemoteUserDataSource {
    suspend fun myLost(): List<MyEntryResponse>
}