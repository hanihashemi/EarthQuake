package com.hanihashemi.earthquake.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.hanihashemi.earthquake.model.FeatureCollection
import com.hanihashemi.earthquake.data.repository.EventRepository
import com.hanihashemi.earthquake.data.db.Resource

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