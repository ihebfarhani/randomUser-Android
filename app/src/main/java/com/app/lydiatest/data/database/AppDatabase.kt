package com.app.lydiatest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.lydiatest.data.database.dao.UsersDao
import com.app.lydiatest.data.database.entity.UsersEntity

@Database(entities = [UsersEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
}