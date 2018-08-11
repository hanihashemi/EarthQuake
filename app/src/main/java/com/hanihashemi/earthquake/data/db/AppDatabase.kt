package com.hanihashemi.earthquake.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.hanihashemi.earthquake.model.Feature
import com.hanihashemi.earthquake.util.FloatConverter

@Database(entities = [Feature::class], version = 1)
@TypeConverters(FloatConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun featureDao(): FeatureDao
}