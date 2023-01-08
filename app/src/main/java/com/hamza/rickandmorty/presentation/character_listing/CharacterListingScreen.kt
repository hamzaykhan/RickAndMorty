package com.hamza.rickandmorty.presentation.character_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CharacterListingScreen(
    viewModel: CharacterListingViewModel= hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.swipeRefresh
    )
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CharacterListingEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(CharacterListingEvent.Refresh)
            }
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(state.characters.size) { i ->
                    if (i >= state.characters.size - 1 && !state.endReached && !state.isLoading) {
                        viewModel.getCharacterListings()
                    }

                    val character = state.characters[i]
                    CharacterItem(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // navigation to detail screen
                            }
                            .padding(12.dp)
                    )


                }

                item {
                    if (state.progressLoader) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                }
            }
        }
    }
}