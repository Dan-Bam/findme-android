package com.example.domain.param.found

data class FoundParam(
    val title: String,
    val description: String,
    val category: String,
    val tags: List<String>,
    val isSafe: Boolean,
    val place: String,
    val latitude: String,
    val longitude: String
)