package com.hanihashemi.earthquake.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.hanihashemi.earthquake.model.Feature


@Dao
interface FeatureDao {
    @Insert(onConflict = REPLACE)
    fun insert(vararg feature: Feature)

    @Query("SELECT * FROM feature WHERE id = :featureId")
    fun load(featureId: String): LiveData<Feature>

    @Query("SELECT * FROM feature")
    fun loadAll(): LiveData<List<Feature>>
}