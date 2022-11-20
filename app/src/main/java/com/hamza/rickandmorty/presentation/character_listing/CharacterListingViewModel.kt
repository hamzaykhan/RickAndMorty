package com.hamza.rickandmorty.presentation.character_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.rickandmorty.domain.repository.RMRepository
import com.hamza.rickandmorty.util.wrapper.Resource
import com.hamza.rickandmorty.util.wrapper.isEmptyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListingViewModel @Inject constructor(
    private val repository: RMRepository
): ViewModel() {

    var state by mutableStateOf(CharacterListingState())

    private var searchJob: Job? = null

    init {
        getCharacterListings()
    }

    fun onEvent(event: CharacterListingEvent) {
        when (event) {
            is CharacterListingEvent.Refresh -> getCharacterListings(fetchFromRemote = true)
            is CharacterListingEvent.onSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchCharacterListings()
                }
            }
        }
    }

    private fun getCharacterListings(fetchFromRemote: Boolean = false, page: Int = 1) {
        viewModelScope.launch {
            repository
                .getRMCharacters(fetchFromRemote, page)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(isLoading = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(characters = result)
                        }

                        is Resource.Empty -> state = state.copy(isEmpty = resource.isEmptyResponse())

                        is Resource.Error -> state = state.copy(isError = true)
                    }
            }
        }
    }

    private fun searchCharacterListings() {
        viewModelScope.launch {
            repository
                .searchCharacter(state.searchQuery)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(isLoading = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(characters = result)
                        }

                        is Resource.Empty -> state = state.copy(isEmpty = resource.isEmptyResponse())

                        is Resource.Error -> state = state.copy(isError = true)
                    }
                }
        }
    }
}