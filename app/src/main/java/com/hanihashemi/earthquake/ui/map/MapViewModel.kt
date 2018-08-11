package com.hanihashemi.earthquake.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.hanihashemi.earthquake.model.FeatureCollection
import com.hanihashemi.earthquake.network.EventRepository
import com.hanihashemi.earthquake.network.Resource
import com.hanihashemi.earthquake.network.Retrofit

class MapViewModel : ViewModel() {
    private lateinit var eventRepo: EventRepository
    private var features: LiveData<Resource<FeatureCollection>>? = null

    fun init(eventRepository: EventRepository) {
        this.eventRepo = eventRepository

        if (features != null)
            return

        features = eventRepository.get()
    }

    fun getEvents() = features!!
}