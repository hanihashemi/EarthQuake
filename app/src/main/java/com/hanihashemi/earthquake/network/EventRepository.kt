package com.hanihashemi.earthquake.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.hanihashemi.earthquake.model.FeatureCollection
import com.hanihashemi.earthquake.util.AppExecutors

class EventRepository(
        val eventService: EventService,
        val featureDao: FeatureDao) {

    fun get(): LiveData<Resource<FeatureCollection>> {
        return object : NetworkBoundResource<FeatureCollection, FeatureCollection>(AppExecutors()) {
            override fun saveCallResult(item: FeatureCollection) {
                featureDao.insert(*item.features.toTypedArray())
            }

            override fun shouldFetch(data: FeatureCollection?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<FeatureCollection> {
                val mutableLiveData = MutableLiveData<FeatureCollection>()
                mutableLiveData.value = FeatureCollection(listOf())
                return mutableLiveData
            }

            override fun createCall(): LiveData<ApiResponse<FeatureCollection>> {
                return eventService.get()
            }

        }.asLiveData()
    }
}