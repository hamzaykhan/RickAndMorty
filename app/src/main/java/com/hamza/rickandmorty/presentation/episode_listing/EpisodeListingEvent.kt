package com.hamza.rickandmorty.presentation.episode_listing

sealed class EpisodeListingEvent {
    object Refresh: EpisodeListingEvent()
    data class onSearchQueryChange(val query: String): EpisodeListingEvent()
}
