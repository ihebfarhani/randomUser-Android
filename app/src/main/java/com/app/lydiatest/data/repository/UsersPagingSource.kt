package com.app.lydiatest.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.lydiatest.data.database.dao.UsersDao
import com.app.lydiatest.data.error.NoInternetError
import com.app.lydiatest.data.mapper.toEntity
import com.app.lydiatest.data.mapper.toModel
import com.app.lydiatest.data.remote.UsersApi
import com.app.lydiatest.data.utils.ApiHandler.handleApi
import com.app.lydiatest.data.utils.NetworkMonitor
import com.app.lydiatest.data.utils.NetworkResult
import com.app.lydiatest.domain.model.User

class UserPagingSource(
    private val userApi: UsersApi,
    private val networkMonitor: NetworkMonitor,
    private val usersDao: UsersDao
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageNumber = params.key ?: 1  // Start from page 1 if null
        return when (val result =
            handleApi(networkMonitor) { userApi.getAllUsers(page = pageNumber) }) {
            is NetworkResult.Success -> {
                val data = result.data.results
                usersDao.insertUsers(data.map { it.toEntity() })
                val nextKey = if (data.isNotEmpty()) pageNumber + 1 else null
                LoadResult.Page(
                    data = data.map { it.toModel() },
                    prevKey = if (pageNumber == 1) null else pageNumber - 1,
                    nextKey = nextKey
                )
            }

            is NetworkResult.Failure -> {
                Log.e("Failure", result.e.toString())
                if (result.e is NoInternetError) {
                    LoadResult.Page(
                        data = usersDao.getAllUsers().map { it.toModel() },
                        prevKey = null,
                        nextKey = null
                    )
                } else {
                    LoadResult.Error(result.e)
                }
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}