package com.hamza.rickandmorty.presentation.episode_listing

import com.hamza.rickandmorty.domain.model.RMEpisode

data class EpisodeListingState(
    val episodes: List<RMEpisode> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val isError: Boolean = false,
    val isEmpty: Boolean = false
)
