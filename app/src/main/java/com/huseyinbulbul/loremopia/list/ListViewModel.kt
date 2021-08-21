package com.huseyinbulbul.loremopia.list

import androidx.lifecycle.MutableLiveData
import com.huseyinbulbul.loremopia.common.BaseViewModel
import com.huseyinbulbul.loremopia.common.data.ListResult
import com.huseyinbulbul.loremopia.common.data.Meditation
import com.huseyinbulbul.loremopia.common.data.Story
import com.huseyinbulbul.loremopia.detail.DetailActivity
import com.huseyinbulbul.loremopia.detail.DetailManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val manager: ListManager): BaseViewModel() {
    val listResultToShow = MutableLiveData<ListResult>()

    fun onCreated(){
        shouldShowActionBar.value = false
        shouldShowLoading.value = true
        manager.getData({
            shouldShowLoading.value = false
            listResultToShow.value = it
        }, {
            shouldShowLoading.value = false
            showMessage.value = it
        })
    }

    fun meditationSelected(meditation: Meditation){
        DetailManager.selectedMeditation = meditation
        openActivity.value = DetailActivity::class.java
    }

    fun storySelected(story: Story){
        DetailManager.selectedStory = story
        openActivity.value = DetailActivity::class.java
    }
}