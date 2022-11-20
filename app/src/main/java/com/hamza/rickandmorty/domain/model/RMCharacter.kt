package com.hamza.rickandmorty.domain.model

data class RMCharacter(
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val page: Int
)
