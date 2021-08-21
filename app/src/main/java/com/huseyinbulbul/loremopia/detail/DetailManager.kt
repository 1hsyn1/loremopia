package com.huseyinbulbul.loremopia.detail

import com.huseyinbulbul.loremopia.common.data.Meditation
import com.huseyinbulbul.loremopia.common.data.Story
import javax.inject.Inject

class DetailManager @Inject constructor() {
    companion object{
        var selectedStory: Story? = null
        var selectedMeditation: Meditation? = null
    }
}