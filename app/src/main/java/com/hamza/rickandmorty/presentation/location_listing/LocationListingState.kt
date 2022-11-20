package com.hamza.rickandmorty.presentation.location_listing

import com.hamza.rickandmorty.domain.model.LocationListing

data class LocationListingState(
    val locations: List<LocationListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val isError: Boolean = false,
    val isEmpty: Boolean = false
)
