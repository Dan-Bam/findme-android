package com.example.data.remote.datasource

import com.example.data.remote.response.user.InfoResponse
import com.example.data.remote.response.user.MyEntryResponse

interface RemoteUserDataSource {
    suspend fun myLost(): List<MyEntryResponse>
    suspend fun myFound(): List<MyEntryResponse>
    suspend fun myInfo(): InfoResponse
    suspend fun logout()
}