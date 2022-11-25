package com.example.data.remote.network

import com.example.data.remote.request.found.EditFoundRequest
import com.example.data.remote.request.found.FoundRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface FoundAPI {
    @POST("found")
    suspend fun registerFound(
        @Body foundRequest: FoundRequest
    )

    @PATCH("found/{foundId}")
    suspend fun editFound(
        @Path("foundId") foundId: String,
        @Body editFoundRequest: EditFoundRequest
    )

    @DELETE("found/{foundId}")
    suspend fun deleteFound(
        @Path("foundId") foundId: String
    )
}