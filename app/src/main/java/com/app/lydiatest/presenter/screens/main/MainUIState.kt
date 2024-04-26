package com.app.lydiatest.presenter.screens.main

import androidx.paging.PagingData
import com.app.lydiatest.domain.model.User
import kotlinx.coroutines.flow.Flow

data class MainUIState(
    val userList: Flow<PagingData<User>>? = null,
    val exception: Throwable? = null,
    val isRefreshing: Boolean = false
)