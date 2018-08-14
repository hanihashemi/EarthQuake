package com.hanihashemi.earthquake.model

import com.neovisionaries.i18n.CountryCode

data class Properties(
        val mag: Float,
        val place: String,
        val title: String,
        val time: Long
) {
    val city: String
        get() = title.substring(title.lastIndexOf("of") + 3)

    val countryCode: String
        get() {
            val countryName = title.substring(title.lastIndexOf(",") + 2)
            val abbreviations = CountryCode.findByName(countryName)

            return if (abbreviations.size == 0) "us" else abbreviations.first().name
        }
}