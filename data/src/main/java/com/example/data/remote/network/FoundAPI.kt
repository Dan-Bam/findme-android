package com.example.data.remote.network

import com.example.data.remote.request.found.FoundRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface FoundAPI {
    @POST("found")
    suspend fun registerFound(
        @Body foundRequest: FoundRequest
    )
}