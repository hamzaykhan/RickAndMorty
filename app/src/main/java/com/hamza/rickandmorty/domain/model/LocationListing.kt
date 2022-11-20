package com.hamza.rickandmorty.domain.model

import java.time.LocalDateTime

data class LocationListing(
    val id: Int,
    val name: String,
    val type: String,
    val created: LocalDateTime,
    val page: Int
)
