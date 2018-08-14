package com.hanihashemi.earthquake.model

import org.junit.Assert.*
import org.junit.Test
import java.util.*


class PropertiesTest{

    @Test
    fun country_code(){
        val countries = hashMapOf<String, String>()

        var properties = Properties(0F, "", "M 1.6 - 67km N of Tonopah, Nevada", 222L)
        assertEquals("us", properties.countryCode)

        properties = Properties(0F, "", "M 1.6 - 67km N of Tonopah, Alaska", 222L)
        assertEquals("us", properties.countryCode)

        properties = Properties(0F, "", "M 1.6 - 67km N of Tonopah, US", 222L)
        assertEquals("us", properties.countryCode)

        properties = Properties(0F, "", "M 1.6 - 67km N of Tonopah, Canada", 222L)
        assertEquals("ca", properties.countryCode)

        properties = Properties(0F, "", "5km W of Lake Henshaw, CA", 222L)
        assertEquals("ca", properties.countryCode)

        properties = Properties(0F, "", "M 1.6 - 67km N of Tonopah, Turkey", 222L)
        assertEquals("tr", properties.countryCode)
    }


}