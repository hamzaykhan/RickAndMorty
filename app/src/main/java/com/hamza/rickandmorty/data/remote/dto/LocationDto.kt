package com.hamza.rickandmorty.data.remote.dto

data class LocationDto(
    val info: InfoDto,
    val results: List<LocationResultDto>
)

data class LocationResultDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)