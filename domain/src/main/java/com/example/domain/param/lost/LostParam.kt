package com.example.domain.param.lost

data class LostParam(
    val title: String,
    val description: String,
    val category: List<Category>,
    val tags: List<String>,
    val isSafe: Boolean,
    val place: String,
    val latitude: String,
    val longitude: String
){
    data class Category(
        val mainCategory: String,
        val subCategory: String
    )
}