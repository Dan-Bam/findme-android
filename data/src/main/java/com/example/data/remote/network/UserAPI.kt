package com.example.data.remote.network

import com.example.data.remote.response.user.MyEntryResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("user/lost")
    suspend fun myLost(): List<MyEntryResponse>
    @GET("user/found")
    suspend fun myFound(): List<MyEntryResponse>
}