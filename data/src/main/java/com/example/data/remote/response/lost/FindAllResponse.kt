package com.example.data.remote.response.lost

import com.example.domain.entity.lost.FindAllEntity
import com.google.gson.annotations.SerializedName

data class FindAllResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("lostImages")
    val lostImages: List<LostImage>,
    @SerializedName("category")
    val category: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("isSafe")
    val isSafe: Boolean
) {
    data class LostImage(
        @SerializedName("id")
        val imageId: String,
        @SerializedName("imageUrl")
        val imageUrl: String,
    )

    fun LostImage.toEntity() = FindAllEntity.LostImage(
        imageId = imageId,
        imageUrl = imageUrl
    )
}

fun FindAllResponse.toEntity() = FindAllEntity(
    id = id,
    title = title,
    description = description,
    place = place,
    lostImages = lostImages.map { it.toEntity() },
    category = category,
    tags = tags,
    isSafe = isSafe
)