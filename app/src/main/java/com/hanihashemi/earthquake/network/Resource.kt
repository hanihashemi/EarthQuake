package com.hanihashemi.earthquake.network

import com.hanihashemi.earthquake.network.Status.*

class Resource<T> private constructor(
        val status: Status,
        val data: T?,
        val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}