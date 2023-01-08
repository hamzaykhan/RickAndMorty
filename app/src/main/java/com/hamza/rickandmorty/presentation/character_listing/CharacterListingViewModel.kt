package com.hamza.rickandmorty.presentation.character_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.rickandmorty.domain.model.CharacterListing
import com.hamza.rickandmorty.domain.repository.RMRepository
import com.hamza.rickandmorty.util.pagination.DefaultPaginator
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

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdate = {
            state = state.copy(
                swipeRefresh = it && state.page == 1,
                progressLoader = it && state.page != 1
            )
        },
        onRequest = { page ->
            repository.getRMCharacters(
                fetchFromRemote = state.fetchFromRemote,
                page = page
            )
        },
        getNextKey = { page ->
            page + 1
        },
        onError = {
            state = state.copy(isError = true)
        },
        onSuccess = { items, newPage ->
            state = state.copy(
                page = newPage,
                characters = state.characters + items,
                fetchFromRemote = false
            )
        },
        onEmpty = {
            state = state.copy(endReached = true)
        },
    )

    init {
        getCharacterListings()
    }

    fun onEvent(event: CharacterListingEvent) {
        when (event) {
            is CharacterListingEvent.Refresh ->  {
                state = state.copy(
                    swipeRefresh = true,
                    page = 1,
                    fetchFromRemote = true,
                    endReached = false,
                    characters = emptyList()
                )
                getCharacterListings()
            }
            is CharacterListingEvent.OnSearchQueryChange -> {
                state = state.copy(
                    searchQuery = event.query,
                    characters = emptyList(),
                    page = 1,
                    endReached = false,
                    fetchFromRemote = true
                )
                searchJob?.cancel()

                searchJob = viewModelScope.launch {
                    delay(500L)

                    if (state.searchQuery.isEmpty())
                        getCharacterListings()
                    else
                        searchCharacterListings()
                }
            }
        }
    }

    fun getCharacterListings() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun searchCharacterListings() {
        viewModelScope.launch {
            repository
                .searchCharacter(state.searchQuery)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> state = state.copy(swipeRefresh = true)

                        is Resource.Success -> resource.data?.let { result ->
                            state = state.copy(
                                characters = result,
                                swipeRefresh = false,
                                endReached = true
                            )
                        }

                        is Resource.Empty -> state = state.copy(
                            isEmpty = true,
                            swipeRefresh = false,
                            endReached = true
                        )

                        is Resource.Error -> state = state.copy(
                            isError = true,
                            swipeRefresh = false,
                            endReached = true
                        )
                    }
                }
        }
    }
}