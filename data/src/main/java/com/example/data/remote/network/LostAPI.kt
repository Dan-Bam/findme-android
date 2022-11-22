package com.example.data.remote.network

import com.example.data.remote.response.lost.FindAllResponse
import retrofit2.http.GET

interface LostAPI {
    @GET("lost/findAll")
    suspend fun findAll(): List<FindAllResponse>
}