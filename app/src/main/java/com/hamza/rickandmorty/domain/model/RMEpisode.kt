package com.hamza.rickandmorty.domain.model

import java.time.LocalDateTime

data class RMEpisode(
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: LocalDateTime,
    val page: Int
)