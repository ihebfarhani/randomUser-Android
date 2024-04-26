package com.app.lydiatest.data.mapper

import com.app.lydiatest.data.database.entity.UsersEntity
import com.app.lydiatest.data.remote.response.Result
import com.app.lydiatest.domain.model.User
import com.app.lydiatest.utils.toCustomDateFormat

fun Result.toModel(): User = User(
    phone = phone,
    birthday = dob.date.toCustomDateFormat(),
    fullName = "${name.title} " + "${name.first} " + name.last,
    gender = gender,
    fullAddress = "${location.country}, " + "${location.city}, " + location.state,
    email = email,
    picture = picture.medium
)

fun Result.toEntity(): UsersEntity = UsersEntity(
    phone = phone,
    birthday = dob.date.toCustomDateFormat(),
    fullName = "${name.title} " + "${name.first} " + name.last,
    gender = gender,
    fullAddress = "${location.country}, " + "${location.city}, " + location.state,
    email = email,
    picture = picture.medium
)

fun UsersEntity.toModel(): User = User(
    phone = phone,
    birthday = birthday,
    fullName = fullName,
    gender = gender,
    fullAddress = fullAddress,
    email = email,
    picture = picture
)