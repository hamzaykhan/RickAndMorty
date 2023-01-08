package com.hamza.rickandmorty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val page: Int,
    val image: String
)
