package com.hamza.rickandmorty.domain.repository

import com.hamza.rickandmorty.domain.model.CharacterListing
import com.hamza.rickandmorty.domain.model.EpisodeListing
import com.hamza.rickandmorty.domain.model.LocationListing
import com.hamza.rickandmorty.util.wrapper.Resource
import kotlinx.coroutines.flow.Flow

interface RMRepository {
    suspend fun getRMCharacters(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<CharacterListing>>>
    suspend fun getRMEpisode(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<EpisodeListing>>>
    suspend fun getRMLocation(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<LocationListing>>>
    suspend fun searchCharacter(query: String): Flow<Resource<List<CharacterListing>>>
    suspend fun searchEpisode(query: String): Flow<Resource<List<EpisodeListing>>>
    suspend fun searchLocation(query: String): Flow<Resource<List<LocationListing>>>
}