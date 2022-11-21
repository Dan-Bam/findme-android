package com.example.data.remote.response.auth

import com.example.domain.entity.auth.TokenEntity
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("expiredAt")
    val expiredAt: String
)

fun TokenResponse.toEntity() = TokenEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    expiredAt = expiredAt
)
