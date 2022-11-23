package com.example.domain.entity.lost


data class FindAllEntity(
    val id: String,
    val title: String,
    val description: String,
    val place: String,
    val lostImages: List<String>,
    val category: List<Category>,
    val tags: List<String>,
    val isSafe: Boolean,
    val latitude: String,
    val longitude: String
) {
    data class Category(
        val mainCategory: String,
        val subCategory: String
    )
}