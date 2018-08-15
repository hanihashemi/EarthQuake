package com.hanihashemi.earthquake.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.hanihashemi.earthquake.data.network.Resource
import com.hanihashemi.earthquake.data.repository.EventRepository
import com.hanihashemi.earthquake.model.Feature

class MapViewModel : ViewModel() {
    private lateinit var eventRepo: EventRepository
    private var features: LiveData<Resource<List<Feature>>>? = null

    fun init(eventRepository: EventRepository) {
        this.eventRepo = eventRepository

        if (features != null)
            return

        features = eventRepository.get()
    }

    fun getEvents() = features!!
}