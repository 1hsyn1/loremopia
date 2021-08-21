package com.huseyinbulbul.loremopia.common.data

data class DetailViewData(
    val title: String?,
    val image: Image?,
    val content: String?,
    val date: Long?
){
    constructor(meditation: Meditation): this(meditation.title,
        meditation.image,
        meditation.content,
        meditation.releaseDate)

    constructor(story: Story): this(story.name,
        story.image,
        story.content,
        story.date)
}