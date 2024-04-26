package com.app.lydiatest.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toCustomDateFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date: Date? = inputFormat.parse(this)
    date?.let {
        return outputFormat.format(date)
    } ?: kotlin.run {
        return ""
    }
}