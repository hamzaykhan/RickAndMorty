package com.hamza.rickandmorty.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toLocalDateTime(): LocalDateTime {
    // 2017-11-10T12:42:04.162Z
    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    return LocalDateTime.parse(this, formatter)
}

fun LocalDateTime.toFormattedString(): String {
    val pattern = "MMMM dd, yyyy"
    return this.format(DateTimeFormatter.ofPattern(pattern))
}