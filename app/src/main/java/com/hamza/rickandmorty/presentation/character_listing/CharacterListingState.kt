package com.hamza.rickandmorty.presentation.character_listing

import com.hamza.rickandmorty.domain.model.CharacterListing

data class CharacterListingState(
    val characters: List<CharacterListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val isError: Boolean = false,
    val isEmpty: Boolean = false
)