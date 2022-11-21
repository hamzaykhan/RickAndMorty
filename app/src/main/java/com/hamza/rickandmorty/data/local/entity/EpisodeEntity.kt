package com.hamza.rickandmorty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
    val page: Int
)
