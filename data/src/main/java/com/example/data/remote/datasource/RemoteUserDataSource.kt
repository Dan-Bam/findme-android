package com.example.data.remote.datasource

import com.example.data.remote.request.user.EditInfoRequest
import com.example.data.remote.response.user.InfoResponse
import com.example.data.remote.response.user.MyFoundResponse
import com.example.data.remote.response.user.MyLostResponse

interface RemoteUserDataSource {
    suspend fun myLost(): List<MyLostResponse>
    suspend fun myFound(): List<MyFoundResponse>
    suspend fun myInfo(): InfoResponse
    suspend fun editInfo(editInfoRequest: EditInfoRequest)
    suspend fun logout()
}