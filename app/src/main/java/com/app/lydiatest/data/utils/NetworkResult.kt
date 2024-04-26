package com.app.lydiatest.data.utils

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val code: Int, val data: T) : NetworkResult<T>()
    class Failure<T : Any>(val e: Exception) : NetworkResult<T>()
}

