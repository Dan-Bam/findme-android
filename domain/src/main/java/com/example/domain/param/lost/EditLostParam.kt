package com.example.domain.param.lost

data class EditLostParam(
    val title: String,
    val description: String,
    val tags: List<String>,
    val isSafe: Boolean,
    val place: String,
    val latitude: String,
    val longitude: String
)