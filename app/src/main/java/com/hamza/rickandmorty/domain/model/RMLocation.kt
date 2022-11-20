package com.hamza.rickandmorty.domain.model

import java.time.LocalDateTime

data class RMLocation(
    val id: Int,
    val name: String,
    val type: String,
    val created: LocalDateTime,
    val page: Int
)
