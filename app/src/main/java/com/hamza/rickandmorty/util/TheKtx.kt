package com.hamza.rickandmorty.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toLocalDateTime(): LocalDateTime {
    val pattern = "yyyy-MM-ddTHH:mm:ss.Z"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    return LocalDateTime.parse(this, formatter)
}