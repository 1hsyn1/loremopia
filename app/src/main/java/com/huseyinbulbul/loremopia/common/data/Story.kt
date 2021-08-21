package com.huseyinbulbul.loremopia.common.data

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("name") val name: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("image") val image: Image?,
    @SerializedName("date") val date: Long?,
    @SerializedName("text") val content: String?
)