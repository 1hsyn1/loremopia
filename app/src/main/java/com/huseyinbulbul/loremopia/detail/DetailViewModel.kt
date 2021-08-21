package com.huseyinbulbul.loremopia.detail

import androidx.lifecycle.MutableLiveData
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.BaseViewModel
import com.huseyinbulbul.loremopia.common.data.DetailViewData
import com.huseyinbulbul.loremopia.common.data.Meditation
import com.huseyinbulbul.loremopia.common.data.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailManager: DetailManager): BaseViewModel() {
    val dataToShow = MutableLiveData<DetailViewData>()
    val soundToPlay = MutableLiveData<String>()
    val shouldPlaySound = MutableLiveData<Boolean>()

    private val soundUrl = "https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3"
    private var isPlaying = false

    fun onCreated(){
        shouldShowLeftIcon.value = true
        setLeftIconDrawableId.value = R.drawable.ic_back

        DetailManager.selectedStory?.let {
            titleStringId.value = R.string.story_detail
            dataToShow.value = DetailViewData(it)
            soundToPlay.value = soundUrl
        } ?: run {
            DetailManager.selectedMeditation?.let {
                titleStringId.value = R.string.meditation_detail
                dataToShow.value = DetailViewData(it)
                soundToPlay.value = soundUrl
            } ?: run {
                close.value = 1
            }
        }
    }

    fun onPlayTouched(){
        if(isPlaying){
            shouldPlaySound.value = false
            isPlaying = false
        }else {
            shouldPlaySound.value = true
            isPlaying = true
        }
    }
}