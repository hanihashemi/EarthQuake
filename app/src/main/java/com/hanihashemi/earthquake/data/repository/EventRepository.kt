package com.hanihashemi.earthquake.data.repository

import android.arch.lifecycle.LiveData
import com.hanihashemi.earthquake.data.db.FeatureDao
import com.hanihashemi.earthquake.data.network.ApiResponse
import com.hanihashemi.earthquake.data.network.EventService
import com.hanihashemi.earthquake.data.network.NetworkBoundResource
import com.hanihashemi.earthquake.data.network.Resource
import com.hanihashemi.earthquake.model.FeatureCollection
import com.hanihashemi.earthquake.util.AppExecutors

class EventRepository(
        val eventService: EventService,
        val featureDao: FeatureDao) {

    fun get(offset: Int = 1, limit: Int = 10): LiveData<Resource<FeatureCollection>> {
        return object : NetworkBoundResource<FeatureCollection>(AppExecutors()) {
            override fun createCall(): LiveData<ApiResponse<FeatureCollection>> =
                    eventService.get(offset, limit)
        }.asLiveData()
    }
}