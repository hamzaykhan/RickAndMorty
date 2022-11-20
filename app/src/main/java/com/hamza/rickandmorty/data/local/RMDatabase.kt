package com.hamza.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hamza.rickandmorty.data.local.converter.LocalDateTimeConverter
import com.hamza.rickandmorty.data.local.dao.CharacterDao
import com.hamza.rickandmorty.data.local.dao.EpisodeDao
import com.hamza.rickandmorty.data.local.dao.LocationDao
import com.hamza.rickandmorty.data.local.entity.CharacterEntity
import com.hamza.rickandmorty.data.local.entity.EpisodeEntity
import com.hamza.rickandmorty.data.local.entity.LocationEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class RMDatabase: RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val episodeDao: EpisodeDao
    abstract val locationDao: LocationDao
}