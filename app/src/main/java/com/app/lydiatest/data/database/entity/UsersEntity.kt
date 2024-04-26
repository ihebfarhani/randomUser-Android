package com.app.lydiatest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersEntity(
    @PrimaryKey
    val email: String,
    val fullName: String,
    val phone: String,
    val birthday: String,
    val gender: String,
    val fullAddress: String,
    val picture: String
)