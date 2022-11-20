package com.hamza.rickandmorty.data.mapper

import com.hamza.rickandmorty.data.local.entity.LocationEntity
import com.hamza.rickandmorty.data.remote.dto.LocationDto
import com.hamza.rickandmorty.domain.model.RMLocation
import com.hamza.rickandmorty.util.Util
import com.hamza.rickandmorty.util.toLocalDateTime

fun LocationDto.toLocation(): List<RMLocation> {
    return this.results.map { location ->
        RMLocation(
            id = location.id,
            name = location.name,
            type = location.type,
            created = location.created.toLocalDateTime(),
            page = Util.getCurrentPage(this.info)
        )
    }
}

fun RMLocation.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        created = created,
        page = page
    )
}

fun LocationEntity.toRMLocation(): RMLocation {
    return RMLocation(
        id = id,
        name = name,
        type = type,
        created = created,
        page = page
    )
}