package com.hanihashemi.earthquake.util

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class FloatConverterTest {
    @Test
    fun stringToListOfFloat() {
        val numbers = "1, 2, 2.2, 20"
        val expected = listOf(1F, 2F, 2.2F, 20F)
        assertThat(FloatConverter().stringToListOfFloat(numbers), `is`(expected))
    }

    @Test
    fun listOfFloatToString() {
        val floatList = listOf(1F, 2F, 2.2F, 20F)
        val expected = "1.0,2.0,2.2,20.0"
        assertEquals(expected, FloatConverter().listOfFloatToString(floatList))
    }
}