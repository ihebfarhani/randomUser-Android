package com.app.lydiatest.di

import android.content.Context
import androidx.room.Room
import com.app.lydiatest.data.database.AppDatabase
import com.app.lydiatest.data.database.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @[Provides Singleton]
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "lydia_users.db").build()

    @[Provides Singleton]
    fun provideDao(usersDatabase: AppDatabase): UsersDao = usersDatabase.getUsersDao()

}