package com.example.domain.entity.user

data class MyFoundEntity(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val place: String,
    val lostImages: List<String>,
    val category: String,
    val tags: List<String>,
    val latitude: String,
    val longitude: String
)