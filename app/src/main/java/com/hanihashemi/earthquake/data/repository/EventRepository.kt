package com.hanihashemi.earthquake.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
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

    fun get(): LiveData<Resource<FeatureCollection>> {
        return object : NetworkBoundResource<FeatureCollection, FeatureCollection>(AppExecutors()) {
            override fun saveCallResult(item: FeatureCollection) =
                    featureDao.insert(*item.features.toTypedArray())

            override fun shouldFetch(data: FeatureCollection?) = true

            override fun loadFromDb(): LiveData<FeatureCollection> {
                val features = featureDao.loadAll()

                val featureCollection = MediatorLiveData<FeatureCollection>()
                featureCollection.addSource(features) {
                    if (it != null)
                        featureCollection.value = FeatureCollection(it)
                    else
                        featureCollection.value = FeatureCollection(listOf())
                }

                return featureCollection
            }

            override fun createCall(): LiveData<ApiResponse<FeatureCollection>> = eventService.get()
        }.asLiveData()
    }
}