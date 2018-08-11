package com.hanihashemi.earthquake.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Feature(
        @PrimaryKey
        val id: String,
        @Embedded
        val properties: Properties,
        @Embedded
        val geometry: Geometry
)