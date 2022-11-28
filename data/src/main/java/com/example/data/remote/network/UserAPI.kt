package com.example.data.remote.network

import com.example.data.remote.request.user.EditInfoRequest
import com.example.data.remote.response.user.InfoResponse
import com.example.data.remote.response.user.MyEntryResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserAPI {
    @GET("user/lost")
    suspend fun myLost(): List<MyEntryResponse>
    @GET("user/found")
    suspend fun myFound(): List<MyEntryResponse>
    @GET("user")
    suspend fun myInfo(): InfoResponse
    @PATCH("user")
    suspend fun editInfo(editInfoRequest: EditInfoRequest)
    @DELETE("user/logout")
    suspend fun logout()
}