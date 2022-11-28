package com.example.data.remote.network

import com.example.data.remote.request.lost.EditLostRequest
import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface LostAPI {
    @Multipart
    @POST("lost")
    suspend fun registerLost(
        @Part("lostDto") lostRequest: LostRequest,
        @Part file: MultipartBody.Part
    )

    @Multipart
    @PATCH("lost/{lostId}")
    suspend fun editLost(
        @Path("lostId") lostId: String,
        @Part("lostDto") editLostRequest: EditLostRequest,
        @Part file: MultipartBody.Part?
    )

    @DELETE("lost/{lostId}")
    suspend fun deleteLost(
        @Path("lostId") lostId: String
    )

    @GET("lost/{lostId}")
    suspend fun detailLost(
        @Path("lostId") lostId: String
    ): LostResponse

    @GET("lost/findAll")
    suspend fun findAll(): List<LostResponse>

    @GET("lost")
    suspend fun findCategory(
        @Query("category") category: String?,
        @Query("place") address: String
    ): List<LostResponse>
}