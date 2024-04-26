package com.app.lydiatest.data.remote.response


import com.google.gson.annotations.SerializedName

data class UsersApiResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)