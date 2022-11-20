package com.hamza.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamza.rickandmorty.data.local.entity.EpisodeEntity
import com.hamza.rickandmorty.data.local.entity.LocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(
        locationEntities: List<LocationEntity>
    )

    @Query("DELETE FROM locationentity")
    suspend fun clearLocationEntities()

    @Query(
        """
            SELECT * 
            FROM locationentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(:query) == LOWER(name) OR
                LOWER(:query) == LOWER(type)
        """
    )
    suspend fun searchLocationEntity(query: String): List<LocationEntity>

    @Query(
        """
            SELECT * 
            FROM locationentity
            WHERE page == :page
        """
    )
    suspend fun getLocationEntities(page: Int): List<LocationEntity>
}