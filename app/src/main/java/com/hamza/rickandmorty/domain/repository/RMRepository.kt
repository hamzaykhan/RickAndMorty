package com.hamza.rickandmorty.domain.repository

import com.hamza.rickandmorty.domain.model.RMCharacter
import com.hamza.rickandmorty.domain.model.RMEpisode
import com.hamza.rickandmorty.domain.model.RMLocation
import com.hamza.rickandmorty.util.wrapper.Resource
import kotlinx.coroutines.flow.Flow

interface RMRepository {
    suspend fun getRMCharacters(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<RMCharacter>>>
    suspend fun getRMEpisode(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<RMEpisode>>>
    suspend fun getRMLocation(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<RMLocation>>>
    suspend fun searchCharacter(query: String): Flow<Resource<List<RMCharacter>>>
    suspend fun searchEpisode(query: String): Flow<Resource<List<RMEpisode>>>
    suspend fun searchLocation(query: String): Flow<Resource<List<RMLocation>>>
}