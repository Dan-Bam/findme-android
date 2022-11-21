package com.example.domain.entity.auth

data class TokenEntity(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: String
)