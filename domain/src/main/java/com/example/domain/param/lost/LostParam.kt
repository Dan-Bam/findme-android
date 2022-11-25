package com.example.domain.param.lost

data class LostParam(
    val title: String,
    val description: String,
    val category: String,
    val tags: List<String>,
    val isSafe: Boolean,
    val place: String,
    val latitude: String,
    val longitude: String
)