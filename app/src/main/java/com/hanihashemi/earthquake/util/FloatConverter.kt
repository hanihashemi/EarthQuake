package com.hanihashemi.earthquake.util

import android.arch.persistence.room.TypeConverter

class FloatConverter {
    @TypeConverter
    fun stringToListOfFloat(value: String): List<Float> {
        return value.split(",").map { it.toFloat() }
    }

    @TypeConverter
    fun listOfFloatToString(list: List<Float>): String {
        return list.joinToString(",")
    }
}