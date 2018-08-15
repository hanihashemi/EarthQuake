package com.hanihashemi.earthquake.data.network

import android.arch.lifecycle.LiveData
import com.hanihashemi.earthquake.model.FeatureCollection
import retrofit2.http.GET
import retrofit2.http.Query

interface EventService {
    @GET("fdsnws/event/1/query?format=geojson")
    fun get(@Query("offset") offset: Int,
            @Query("limit") limit: Int): LiveData<ApiResponse<FeatureCollection>>
}