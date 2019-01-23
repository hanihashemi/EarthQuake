package com.hanihashemi.earthquake.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import com.hanihashemi.earthquake.util.AppExecutors

abstract class NetworkBoundResource<ResultType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading()
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.mainThread().execute {
                        result.value = Resource.success(response.body)
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        result.value = Resource.success(null)
                    }
                }
                is ApiErrorResponse -> {
                    appExecutors.mainThread().execute {
                        result.value = Resource.error(response.errorMessage)
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<ResultType>>
}