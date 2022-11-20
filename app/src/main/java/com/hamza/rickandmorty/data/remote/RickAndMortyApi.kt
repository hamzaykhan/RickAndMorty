package com.hamza.rickandmorty.data.remote

import com.hamza.rickandmorty.data.remote.dto.CharacterDto
import com.hamza.rickandmorty.data.remote.dto.EpisodeDto
import com.hamza.rickandmorty.data.remote.dto.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): CharacterDto

    @GET("character")
    suspend fun searchCharacters(
        @Query("name") name: String,
        @Query("gender") gender: String,
        @Query("species") species: String
    ): CharacterDto

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int = 1
    ): LocationDto

    @GET("location")
    suspend fun searchLocation(
        @Query("name") name: String
    ): LocationDto

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int = 1
    ): EpisodeDto

    @GET("episode")
    suspend fun searchEpisodes(
        @Query("name") name: String,
        @Query("episode") episode: String
    ): EpisodeDto

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}