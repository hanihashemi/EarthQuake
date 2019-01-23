package com.hanihashemi.earthquake.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.hanihashemi.earthquake.data.network.Resource
import com.hanihashemi.earthquake.data.repository.EventRepository
import com.hanihashemi.earthquake.model.FeatureCollection

class MapViewModel : ViewModel() {
    private lateinit var eventRepo: EventRepository
    private var trigger = MutableLiveData<Int>()
    val features: LiveData<Resource<FeatureCollection>>

    init {
        features = Transformations.switchMap(trigger) { eventRepo.get(it) }
    }

    fun init(eventRepo: EventRepository) {
        this.eventRepo = eventRepo
    }

    fun nextPage(offset: Int = 1) {
        trigger.value = offset
    }
}