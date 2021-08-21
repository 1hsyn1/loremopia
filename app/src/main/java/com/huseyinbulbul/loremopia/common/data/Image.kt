package com.huseyinbulbul.loremopia.common.data

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("small") val small: String?,
    @SerializedName("large") val large: String?
)