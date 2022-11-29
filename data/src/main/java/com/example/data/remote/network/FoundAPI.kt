package com.example.data.remote.network

import com.example.data.remote.request.found.EditFoundRequest
import com.example.data.remote.request.found.FoundRequest
import com.example.data.remote.response.found.FoundResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface FoundAPI {
    @Multipart
    @POST("found")
    suspend fun registerFound(
        @Part("foundDto") foundRequest: FoundRequest,
        @Part file: MultipartBody.Part
    )

    @Multipart
    @PATCH("found/{foundId}")
    suspend fun editFound(
        @Path("foundId") foundId: String,
        @Part("foundDto") editFoundRequest: EditFoundRequest,
        @Part file: MultipartBody.Part?
    )

    @DELETE("found/{foundId}")
    suspend fun deleteFound(
        @Path("foundId") foundId: String
    )

    @GET("found/{foundId}")
    suspend fun detailFound(
        @Path("foundId") foundId: String
    ): FoundResponse
}