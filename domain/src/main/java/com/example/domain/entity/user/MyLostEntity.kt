package com.example.domain.entity.user

data class MyLostEntity(
    val id: String,
    val userId: String,
    val isMine: Boolean,
    val title: String,
    val description: String,
    val place: String,
    val lostImages: List<String>,
    val category: String,
    val tags: List<String>,
    val isSafe: Boolean,
    val latitude: String,
    val longitude: String
)