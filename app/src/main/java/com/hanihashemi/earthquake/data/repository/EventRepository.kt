package com.hanihashemi.earthquake.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.hanihashemi.earthquake.data.db.FeatureDao
import com.hanihashemi.earthquake.data.network.ApiResponse
import com.hanihashemi.earthquake.data.network.EventService
import com.hanihashemi.earthquake.data.network.NetworkBoundResource
import com.hanihashemi.earthquake.data.network.Resource
import com.hanihashemi.earthquake.model.Feature
import com.hanihashemi.earthquake.model.FeatureCollection
import com.hanihashemi.earthquake.model.Properties
import com.hanihashemi.earthquake.util.AppExecutors

class EventRepository(
        val eventService: EventService,
        val featureDao: FeatureDao) {

    fun get(): LiveData<Resource<List<Feature>>> {
        return object : NetworkBoundResource<List<Feature>, FeatureCollection>(AppExecutors()) {
            override fun saveCallResult(item: FeatureCollection) =
                    featureDao.insert(*item.features.toTypedArray())

            override fun shouldFetch(data: List<Feature>?) = true

            override fun loadFromDb(): LiveData<List<Feature>> = featureDao.loadAll()

            override fun createCall(): LiveData<ApiResponse<FeatureCollection>> = eventService.get(10)
        }.asLiveData()
    }
}