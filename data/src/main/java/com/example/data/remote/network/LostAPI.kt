package com.example.data.remote.network

import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LostAPI {
    @POST("lost")
    suspend fun registerLost(
        @Body lostRequest: LostRequest
    )

    @GET("lost/findAll")
    suspend fun findAll(): List<LostResponse>
}