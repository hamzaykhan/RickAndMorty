package com.hamza.rickandmorty.presentation.location_listing

sealed class LocationListingEvent {
    object Refresh: LocationListingEvent()
    data class onSearchQueryChange(val query: String): LocationListingEvent()
}
