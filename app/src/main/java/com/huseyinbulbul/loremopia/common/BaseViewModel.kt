package com.huseyinbulbul.loremopia.common

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val shouldShowActionBar = MutableLiveData(true)
    val shouldShowLoading = MutableLiveData(false)
    val shouldShowRightIcon = MutableLiveData(false)
    val shouldShowLeftIcon = MutableLiveData(false)
    val hideKeyboard = MutableLiveData(-1)
    val titleText = MutableLiveData("")
    val titleStringId = MutableLiveData(-1)
    val setRightIconBitmap = MutableLiveData<Bitmap>(null)
    val setRightIconDrawableId = MutableLiveData(-1)
    val setLeftIconBitmap = MutableLiveData<Bitmap>(null)
    val setLeftIconDrawableId = MutableLiveData(-1)
    val setOnRightIconClicked = MutableLiveData<View.OnClickListener>(null)
    val openActivity = MutableLiveData<Class<*>>(null)
    val showMessage = MutableLiveData("")
    val showMessageStringId = MutableLiveData(-1)
    val close = MutableLiveData(-1)
}