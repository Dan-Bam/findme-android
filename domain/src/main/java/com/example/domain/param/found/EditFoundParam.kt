package com.example.domain.param.found

data class EditFoundParam(
    val title: String,
    val description: String,
    val tags: List<String>,
    val place: String,
    val latitude: String,
    val longitude: String
)