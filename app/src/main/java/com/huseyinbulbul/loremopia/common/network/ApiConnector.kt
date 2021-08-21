package com.huseyinbulbul.loremopia.common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConnector {
    companion object{
        private var api: LoremopiaApi? = null

        fun getApi(): LoremopiaApi{
            if(api == null){
                api = getRetrofit("https://media.meditopia.com/").create(LoremopiaApi::class.java)
            }

            return api as LoremopiaApi
        }

        fun getRetrofit(baseUrl: String): Retrofit {
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.connectTimeout(120, TimeUnit.SECONDS)
            builder.readTimeout(120, TimeUnit.SECONDS)
            builder.writeTimeout(120, TimeUnit.SECONDS)

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}