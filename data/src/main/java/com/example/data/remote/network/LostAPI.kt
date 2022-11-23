package com.example.data.remote.network

import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface LostAPI {
    @POST("lost")
    suspend fun registerLost(
        @Body lostRequest: LostRequest
    )

    @PATCH("lost/{lostId}")
    suspend fun editLost(
        @Path("lostId") lostId: String,
        @Body lostRequest: LostRequest
    )

    @DELETE("lost/{lostId}")
    suspend fun deleteLost(
        @Path("lostId") lostId: String
    )

    @GET("lost/findAll")
    suspend fun findAll(): List<LostResponse>
}