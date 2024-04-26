package com.app.lydiatest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.lydiatest.data.database.dao.UsersDao
import com.app.lydiatest.data.remote.UsersApi
import com.app.lydiatest.data.utils.NetworkMonitor
import com.app.lydiatest.domain.model.User
import com.app.lydiatest.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userApi: UsersApi,
    private val usersDao: UsersDao,
    private val networkMonitor: NetworkMonitor,
) : UsersRepository {

    override fun getAllUser(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(userApi, networkMonitor, usersDao) }
        ).flow
    }
}