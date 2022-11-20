package com.hamza.rickandmorty.presentation.episode_listing

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

class EpisodeListingViewModel @Inject constructor(
    private val repository: RMRepository
): ViewModel() {
    var state by mutableStateOf(EpisodeListingState())

    private var searchJob: Job? = null

    init {
        getEpisodeListings()
    }

    fun onEvent(event: EpisodeListingEvent) {
        when (event) {
            is EpisodeListingEvent.Refresh -> getEpisodeListings(fetchFromRemote = true)
            is EpisodeListingEvent.onSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchEpisodeListings()
                }
            }
        }
    }

    private fun getEpisodeListings(fetchFromRemote: Boolean = false, page: Int = 1) {
        viewModelScope.launch {
            repository
                .getRMEpisode(fetchFromRemote, page)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(isLoading = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(episodes = result)
                        }

                        is Resource.Empty -> state = state.copy(isEmpty = resource.isEmptyResponse())

                        is Resource.Error -> state = state.copy(isError = true)
                    }
                }
        }
    }

    private fun searchEpisodeListings() {
        viewModelScope.launch {
            repository
                .searchEpisode(state.searchQuery)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(isLoading = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(episodes = result)
                        }

                        is Resource.Empty -> state = state.copy(isEmpty = resource.isEmptyResponse())

                        is Resource.Error -> state = state.copy(isError = true)
                    }
                }
        }
    }
}