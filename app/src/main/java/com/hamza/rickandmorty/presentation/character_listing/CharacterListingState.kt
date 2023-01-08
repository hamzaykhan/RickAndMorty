package com.hamza.rickandmorty.presentation.character_listing

import com.hamza.rickandmorty.domain.model.CharacterListing

data class CharacterListingState(
    val characters: List<CharacterListing> = emptyList(),
    val searchQuery: String = "",
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val endReached: Boolean = false,
    val page: Int = 1,
    val swipeRefresh: Boolean = false,
    val progressLoader: Boolean = false,
    val fetchFromRemote: Boolean = false
) {
    val isLoading = swipeRefresh || progressLoader
}