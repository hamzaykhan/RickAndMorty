package com.hamza.rickandmorty.data.mapper

import com.hamza.rickandmorty.data.local.entity.CharacterEntity
import com.hamza.rickandmorty.data.remote.dto.CharacterDto
import com.hamza.rickandmorty.domain.model.RMCharacter
import com.hamza.rickandmorty.util.Util

fun CharacterDto.toCharacter(): List<RMCharacter> {
    return this.results.map { character ->
         RMCharacter(
            id = character.id,
            name = character.name,
            gender = character.gender,
            species = character.species,
            status = character.status,
            page = Util.getCurrentPage(this.info)
         )
    }
}

fun RMCharacter.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        page = page
    )
}

fun CharacterEntity.toRMCharacter(): RMCharacter {
    return RMCharacter(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        page = page
    )
}