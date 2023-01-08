package com.hamza.rickandmorty.data.repository

import com.hamza.rickandmorty.data.local.RMDatabase
import com.hamza.rickandmorty.data.mapper.*
import com.hamza.rickandmorty.data.remote.RickAndMortyApi
import com.hamza.rickandmorty.domain.model.CharacterListing
import com.hamza.rickandmorty.domain.model.EpisodeListing
import com.hamza.rickandmorty.domain.model.LocationListing
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
    override suspend fun getRMCharacters(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<CharacterListing>>> = flow {
        emit(Resource.Loading)

        val localCharacters = rmDb.characterDao.getCharacterEntities(page = page)

        val shouldJustLoadFromCache = localCharacters.isNotEmpty() && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localCharacters.map { it.toCharacterListing() }))
        } else {
            val result = callApi {
                val data = rmApi.getCharacters(page = page).toCharacter()
                rmDb.characterDao.insertCharacters(data.map { it.toCharacterEntity() })
                data
            }
            emit(result)
        }
    }

    override suspend fun getRMEpisode(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<EpisodeListing>>> = flow {
        emit(Resource.Loading)

        val localEpisodes = rmDb.episodeDao.getEpisodeEntities(page = page)

        val shouldJustLoadFromCache = localEpisodes.isNotEmpty() && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localEpisodes.map { it.toEpisodeListing() }))
        } else {
            val result = callApi {
                val data = rmApi.getEpisodes(page = page).toEpisode()
                rmDb.episodeDao.insertEpisodes(data.map { it.toEpisodeEntity() })
                data
            }
            emit(result)
        }
    }

    override suspend fun getRMLocation(fetchFromRemote: Boolean, page: Int): Flow<Resource<List<LocationListing>>> = flow {
        emit(Resource.Loading)

        val localLocation = rmDb.locationDao.getLocationEntities(page = page)

        val shouldJustLoadFromCache = localLocation.isNotEmpty() && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localLocation.map { it.toLocationListing() }))
        } else {
            val result = callApi {
                val data = rmApi.getLocations(page = page).toLocation()
                rmDb.locationDao.insertLocations(data.map { it.toLocationEntity() })
                data
            }
            emit(result)
        }
    }

    override suspend fun searchCharacter(query: String): Flow<Resource<List<CharacterListing>>> = flow {
        emit(Resource.Loading)

        val localCharacters = rmDb.characterDao.searchCharacterEntity(query = query)

        val shouldJustLoadFromCache = localCharacters.isNotEmpty()
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localCharacters.map { it.toCharacterListing() }))
        }

        val result = callApi {
            rmApi.searchCharacters(name = query, gender = query, species = query).toCharacter().also {
                rmDb.characterDao.insertCharacters(it.map { item -> item.toCharacterEntity() })
            }
        }
        emit(result)
    }

    override suspend fun searchEpisode(query: String): Flow<Resource<List<EpisodeListing>>> = flow {
        emit(Resource.Loading)

        val localEpisodes = rmDb.episodeDao.searchEpisodeEntity(query = query)

        val shouldJustLoadFromCache = localEpisodes.isNotEmpty()
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localEpisodes.map { it.toEpisodeListing() }))
        }

        val result = callApi {
            val data = rmApi.searchEpisodes(name = query, episode = query).toEpisode()
            rmDb.episodeDao.insertEpisodes(data.map { it.toEpisodeEntity() })
            data
        }
        emit(result)
    }

    override suspend fun searchLocation(query: String): Flow<Resource<List<LocationListing>>> = flow {
        emit(Resource.Loading)

        val localLocations = rmDb.locationDao.searchLocationEntity(query = query)

        val shouldJustLoadFromCache = localLocations.isNotEmpty()
        if (shouldJustLoadFromCache) {
            emit(Resource.Success(localLocations.map { it.toLocationListing() }))
        }

        val result = callApi {
            val data = rmApi.searchLocation(name = query).toLocation()
            rmDb.locationDao.insertLocations(data.map { it.toLocationEntity() })
            data
        }
        emit(result)
    }
}