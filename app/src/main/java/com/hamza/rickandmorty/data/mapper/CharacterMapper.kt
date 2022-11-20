package com.hamza.rickandmorty.data.mapper

import com.hamza.rickandmorty.data.local.entity.CharacterEntity
import com.hamza.rickandmorty.data.remote.dto.CharacterDto
import com.hamza.rickandmorty.domain.model.CharacterListing
import com.hamza.rickandmorty.util.Util

fun CharacterDto.toCharacter(): List<CharacterListing> {
    return this.results.map { character ->
         CharacterListing(
            id = character.id,
            name = character.name,
            gender = character.gender,
            species = character.species,
            status = character.status,
            page = Util.getCurrentPage(this.info)
         )
    }
}

fun CharacterListing.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        page = page
    )
}

fun CharacterEntity.toCharacterListing(): CharacterListing {
    return CharacterListing(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        page = page
    )
}