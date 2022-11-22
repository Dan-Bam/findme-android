package com.example.domain.entity.lost


data class FindAllEntity(
    val id: String,
    val title: String,
    val description: String,
    val place: String,
    val lostImages: List<LostImage>,
    val category: String,
    val tags: List<String>,
    val isSafe: Boolean
) {
    data class LostImage(
        val imageId: String,
        val imageUrl: String
    )
}