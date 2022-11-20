package com.hamza.rickandmorty.data.remote.dto

data class EpisodeDto(
    val info: InfoDto,
    val results: List<EpisodeResultDto>
)

data class EpisodeResultDto(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
