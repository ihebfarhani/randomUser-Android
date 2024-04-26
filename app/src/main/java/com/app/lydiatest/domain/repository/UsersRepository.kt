package com.app.lydiatest.domain.repository

import androidx.paging.PagingData
import com.app.lydiatest.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getAllUser(): Flow<PagingData<User>>

}