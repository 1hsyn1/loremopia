package com.huseyinbulbul.loremopia.common.data

import com.google.gson.annotations.SerializedName

data class Meditation(
    @SerializedName("title") val title: String?,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("image") val image: Image?,
    @SerializedName("releaseDate") val releaseDate: Long?,
    @SerializedName("content") val content: String
)