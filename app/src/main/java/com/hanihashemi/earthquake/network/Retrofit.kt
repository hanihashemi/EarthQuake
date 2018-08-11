package com.hanihashemi.earthquake.network

import com.hanihashemi.earthquake.BuildConfig
import com.hanihashemi.earthquake.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val base_url = "https://earthquake.usgs.gov/"
    private var retrofit: Retrofit

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
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    // TODO it should be singleton, and it should inject right to repositories
    fun event(): EventService = retrofit.create(EventService::class.java)
}