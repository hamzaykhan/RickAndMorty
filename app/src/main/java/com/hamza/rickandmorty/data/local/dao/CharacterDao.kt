package com.hamza.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamza.rickandmorty.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(
        characterEntities: List<CharacterEntity>
    )

    @Query("DELETE FROM characterentity")
    suspend fun clearCharacterEntities()

    @Query(
        """
            SELECT * 
            FROM characterentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(:query) == LOWER(gender) OR
                LOWER(:query) == LOWER(species)
        """
    )
    suspend fun searchCharacterEntity(query: String): List<CharacterEntity>
}