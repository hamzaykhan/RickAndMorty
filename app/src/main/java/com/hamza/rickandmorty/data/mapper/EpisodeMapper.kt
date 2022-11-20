package com.hamza.rickandmorty.data.mapper

import com.hamza.rickandmorty.data.local.entity.EpisodeEntity
import com.hamza.rickandmorty.data.remote.dto.EpisodeDto
import com.hamza.rickandmorty.domain.model.EpisodeListing
import com.hamza.rickandmorty.util.Util
import com.hamza.rickandmorty.util.toLocalDateTime

fun EpisodeDto.toEpisode(): List<EpisodeListing> {
    return this.results.map { episode ->
        EpisodeListing(
            id= episode.id,
            name= episode.name,
            episode = episode.episode,
            air_date = episode.air_date.toLocalDateTime(),
            page = Util.getCurrentPage(this.info)
        )
    }
}

fun EpisodeListing.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        page = page
    )
}

fun EpisodeEntity.toEpisodeListing(): EpisodeListing {
    return EpisodeListing(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        page = page
    )
}