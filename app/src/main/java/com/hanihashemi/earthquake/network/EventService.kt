package com.hanihashemi.earthquake.network

import android.arch.lifecycle.LiveData
import com.hanihashemi.earthquake.model.Feature
import retrofit2.Call
import retrofit2.http.GET

interface EventService {
    @GET("fdsnws/event/1/query?format=geojson&limit=10")
    fun get(): Call<Feature>
}