package com.app.lydiatest.di

import com.app.lydiatest.data.database.dao.UsersDao
import com.app.lydiatest.data.remote.UsersApi
import com.app.lydiatest.data.repository.UsersRepositoryImpl
import com.app.lydiatest.data.utils.NetworkMonitor
import com.app.lydiatest.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @[Provides Singleton]
    fun provideUsersRepository(
        userApi: UsersApi,
        usersDao: UsersDao,
        networkMonitor: NetworkMonitor
    ): UsersRepository {
        return UsersRepositoryImpl(userApi, usersDao, networkMonitor)
    }

}