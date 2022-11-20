package com.hamza.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamza.rickandmorty.data.local.entity.CharacterEntity
import com.hamza.rickandmorty.data.local.entity.EpisodeEntity

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(
        episodeEntities: List<EpisodeEntity>
    )

    @Query("DELETE FROM episodeentity")
    suspend fun clearEpisodeEntities()

    @Query(
        """
            SELECT * 
            FROM episodeentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(:query) == LOWER(name) OR
                LOWER(:query) == LOWER(episode)
        """
    )
    suspend fun searchEpisodeEntity(query: String): List<EpisodeEntity>

    @Query(
        """
            SELECT * 
            FROM episodeentity
            WHERE page == :page
        """
    )
    suspend fun getEpisodeEntities(page: Int): List<EpisodeEntity>
}