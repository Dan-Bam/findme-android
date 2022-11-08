package com.example.data.remote.response

import com.example.domain.entity.RefreshEntity
import com.google.gson.annotations.SerializedName

data class RefreshResponse(
    @SerializedName("newAccessToken")
    val accessToken: String,
    @SerializedName("newRefreshToken")
    val refreshToken: String
)

fun RefreshResponse.toEntity() = RefreshEntity(
    accessToken = accessToken,
    refreshToken = refreshToken
)