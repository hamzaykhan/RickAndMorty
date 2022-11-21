package com.hamza.rickandmorty.domain.model


data class EpisodeListing(
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
    val page: Int
)