package com.hamza.rickandmorty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class LocationEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val type: String,
    val created: LocalDateTime,
    val page: Int
)
