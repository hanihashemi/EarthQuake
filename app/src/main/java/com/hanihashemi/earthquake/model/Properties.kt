package com.hanihashemi.earthquake.model

import android.text.format.DateUtils

data class Properties(
        val mag: Float,
        val place: String,
        val title: String,
        val time: Long
) {
    val city: String
        get() = title.substring(title.indexOf("of") + 3)
}