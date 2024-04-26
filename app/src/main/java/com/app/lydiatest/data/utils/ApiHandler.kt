package com.app.lydiatest.data.utils

import com.app.lydiatest.data.error.NoInternetError
import com.app.lydiatest.data.error.UnknownAppError
import kotlinx.coroutines.flow.first
import retrofit2.Response

object ApiHandler {

    suspend fun <T : Any> handleApi(
        networkMonitor: NetworkMonitor,
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val networkState = networkMonitor.networkState.first()
            if (!networkState.isAvailable()) {
                return NetworkResult.Failure(NoInternetError()) // Return NetworkResult with NoInternetError
            }
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(response.code(), body)
            } else {
                //Here We can add our Custom Exception if We want as Exemples :
                // No Data Found or code Erreur 500 ..
                NetworkResult.Failure(UnknownAppError())
            }
        } catch (e: Exception) {
            NetworkResult.Failure(e)
        }

    }
}