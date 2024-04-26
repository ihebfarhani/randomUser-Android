package com.app.lydiatest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.lydiatest.data.database.entity.UsersEntity

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(usersEntity: List<UsersEntity>)

    @Query("SELECT * FROM UsersEntity")
    suspend fun getAllUsers(): List<UsersEntity>

    @Query("DELETE FROM UsersEntity")
    suspend fun clearUsers()
}