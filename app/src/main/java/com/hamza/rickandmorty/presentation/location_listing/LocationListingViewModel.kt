package com.hamza.rickandmorty.presentation.location_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.rickandmorty.domain.repository.RMRepository
import com.hamza.rickandmorty.util.wrapper.Resource
import com.hamza.rickandmorty.util.wrapper.isEmptyResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListingViewModel @Inject constructor(
    private val repository: RMRepository
): ViewModel() {
    var state by mutableStateOf(LocationListingState())

    private var searchJob: Job? = null

    init {
        getLocationListings()
    }

    fun onEvent(event: LocationListingEvent) {
        when (event) {
            is LocationListingEvent.Refresh -> getLocationListings(fetchFromRemote = true)
            is LocationListingEvent.onSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchLocationListings()
                }
            }
        }
    }

    private fun getLocationListings(fetchFromRemote: Boolean = false, page: Int = 1) {
        viewModelScope.launch {
            repository
                .getRMLocation(fetchFromRemote, page)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(isLoading = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(locations = result)
                        }

                        is Resource.Empty -> state = state.copy(isEmpty = resource.isEmptyResponse())

                        is Resource.Error -> state = state.copy(isError = true)
                    }
                }
        }
    }

    private fun searchLocationListings() {
        viewModelScope.launch {
            repository
                .searchLocation(state.searchQuery)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(isLoading = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(locations = result)
                        }

                        is Resource.Empty -> state = state.copy(isEmpty = resource.isEmptyResponse())

                        is Resource.Error -> state = state.copy(isError = true)
                    }
                }
        }
    }
}