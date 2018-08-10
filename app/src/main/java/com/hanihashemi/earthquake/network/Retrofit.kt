package com.hanihashemi.earthquake.network

import com.hanihashemi.earthquake.BuildConfig
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    private const val base_url = "https://earthquake.usgs.gov/"
    var retrofit: Retrofit

    init {
        val client = OkHttpClient().newBuilder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun event(): EventService = retrofit.create(EventService::class.java)
}