package com.hamza.rickandmorty.data.mapper

import com.hamza.rickandmorty.data.local.entity.EpisodeEntity
import com.hamza.rickandmorty.data.remote.dto.EpisodeDto
import com.hamza.rickandmorty.domain.model.RMEpisode
import com.hamza.rickandmorty.util.Util
import com.hamza.rickandmorty.util.toLocalDateTime

fun EpisodeDto.toEpisode(): List<RMEpisode> {
    return this.results.map { episode ->
        RMEpisode(
            id= episode.id,
            name= episode.name,
            episode = episode.episode,
            air_date = episode.air_date.toLocalDateTime(),
            page = Util.getCurrentPage(this.info)
        )
    }
}

fun RMEpisode.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        page = page
    )
}

fun EpisodeEntity.toRMEpisode(): RMEpisode {
    return RMEpisode(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        page = page
    )
}