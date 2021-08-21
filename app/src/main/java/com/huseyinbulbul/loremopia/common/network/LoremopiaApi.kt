package com.huseyinbulbul.loremopia.common.network

import com.huseyinbulbul.loremopia.common.data.ListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LoremopiaApi {
    @GET("files/MobileInterviewProjectData.json")
    fun getList(): Observable<ListResult>
}