package com.huseyinbulbul.loremopia.login

import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun onCreated(){
        shouldShowActionBar.value = false
    }

    fun continueTouched(username: String, password: String){
        if(username.length < 3) {
            showMessageStringId.value = R.string.short_username
            return
        }

        if(!("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}\$").toRegex().containsMatchIn(password)){
            showMessageStringId.value = R.string.wrong_password
            return
        }
    }
}