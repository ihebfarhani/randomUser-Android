package com.app.lydiatest.data.remote

import com.app.lydiatest.contants.Constants.COUNT
import com.app.lydiatest.contants.Constants.PAGE
import com.app.lydiatest.contants.Constants.SEED
import com.app.lydiatest.data.remote.response.UsersApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("api/1.3/")
    suspend fun getAllUsers(
        @Query("seed") seed: String = SEED,
        @Query("results") count: Int = COUNT,
        @Query("page") page: Int = PAGE,
    ): Response<UsersApiResponse>
}