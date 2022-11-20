package com.hamza.rickandmorty.data.repository

import com.hamza.rickandmorty.data.local.RMDatabase
import com.hamza.rickandmorty.data.mapper.*
import com.hamza.rickandmorty.data.remote.RickAndMortyApi
import com.hamza.rickandmorty.domain.model.RMCharacter
import com.hamza.rickandmorty.domain.model.RMEpisode
import com.hamza.rickandmorty.domain.model.RMLocation
import com.hamza.rickandmorty.domain.repository.RMRepository
import com.hamza.rickandmorty.util.wrapper.Resource
import com.hamza.rickandmorty.util.wrapper.callApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RMRepositoryImpl @Inject constructor(
    private val rmApi: RickAndMortyApi,
    private val rmDb: RMDatabase
): RMRepository {
    override suspend fun getRMCharacters(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<RMCharacter>>> = flow {
        emit(Resource.Loading)

        val localCharacters = rmDb.characterDao.getCharacterEntities(page = page)

        val shouldJustLoadFromCache = localCharacters.isNotEmpty() && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localCharacters.map { it.toRMCharacter() }))
        } else {
            val result = callApi {
                rmApi.getCharacters(page = page).toCharacter()
            }
            emit(result)
        }
    }

    override suspend fun getRMEpisode(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<RMEpisode>>> = flow {
        emit(Resource.Loading)

        val localEpisodes = rmDb.episodeDao.getEpisodeEntities(page = page)

        val shouldJustLoadFromCache = localEpisodes.isNotEmpty() && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localEpisodes.map { it.toRMEpisode() }))
        } else {
            val result = callApi {
                rmApi.getEpisodes(page = page).toEpisode()
            }
            emit(result)
        }
    }

    override suspend fun getRMLocation(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<RMLocation>>> = flow {
        emit(Resource.Loading)

        val localLocation = rmDb.locationDao.getLocationEntities(page = page)

        val shouldJustLoadFromCache = localLocation.isNotEmpty() && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localLocation.map { it.toRMLocation() }))
        } else {
            val result = callApi {
                rmApi.getLocations(page = page).toLocation()
            }
            emit(result)
        }
    }

    override suspend fun searchCharacter(query: String): Flow<Resource<List<RMCharacter>>> = flow {
        emit(Resource.Loading)

        val localCharacters = rmDb.characterDao.searchCharacterEntity(query = query)

        val shouldJustLoadFromCache = localCharacters.isNotEmpty()
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localCharacters.map { it.toRMCharacter() }))
        }

        val result = callApi {
            rmApi.searchCharacters(name = query, gender = query, species = query).toCharacter()
        }
        emit(result)
    }

    override suspend fun searchEpisode(query: String): Flow<Resource<List<RMEpisode>>> = flow {
        emit(Resource.Loading)

        val localEpisodes = rmDb.episodeDao.searchEpisodeEntity(query = query)

        val shouldJustLoadFromCache = localEpisodes.isNotEmpty()
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localEpisodes.map { it.toRMEpisode() }))
        }

        val result = callApi {
            rmApi.searchEpisodes(name = query, episode = query).toEpisode()
        }
        emit(result)
    }

    override suspend fun searchLocation(query: String): Flow<Resource<List<RMLocation>>> = flow {
        emit(Resource.Loading)

        val localLocations = rmDb.locationDao.searchLocationEntity(query = query)

        val shouldJustLoadFromCache = localLocations.isNotEmpty()
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localLocations.map { it.toRMLocation() }))
        }

        val result = callApi {
            rmApi.searchLocation(name = query).toLocation()
        }
        emit(result)
    }
}