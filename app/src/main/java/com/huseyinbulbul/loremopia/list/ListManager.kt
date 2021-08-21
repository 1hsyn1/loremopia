package com.huseyinbulbul.loremopia.list

import com.huseyinbulbul.loremopia.common.data.ListResult
import com.huseyinbulbul.loremopia.common.network.ApiConnector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListManager @Inject constructor() {
    fun getData(success: (ListResult) -> Unit, error: (String) -> Unit){
        ApiConnector.getApi()
            .getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success(it)
            }, {
                error(it.message.orEmpty())
            })
    }
}