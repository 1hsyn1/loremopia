package com.huseyinbulbul.loremopia.common.data

import com.google.gson.annotations.SerializedName

data class ListResult(
    @SerializedName("isBannerEnabled") val isBannerEnabled: Boolean?,
    @SerializedName("meditations") val meditations: List<Meditation>?,
    @SerializedName("stories") val stories: List<Story>?
)