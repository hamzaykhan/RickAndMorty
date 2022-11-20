package com.hamza.rickandmorty.presentation.character_listing

sealed class CharacterListingEvent {
    object Refresh: CharacterListingEvent()
    data class onSearchQueryChange(val query: String): CharacterListingEvent()
}

