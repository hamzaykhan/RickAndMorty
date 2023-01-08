package com.hamza.rickandmorty.domain.model

import androidx.compose.ui.graphics.Color

data class CharacterListing(
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val page: Int,
    val image: String
) {

    fun statusColor(): Color {
        return if (status == "Alive") {
            Color.Green
        } else {
            Color.Red
        }
    }
}

