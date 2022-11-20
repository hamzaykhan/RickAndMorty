package com.hamza.rickandmorty.data.mapper

import com.hamza.rickandmorty.data.local.entity.LocationEntity
import com.hamza.rickandmorty.data.remote.dto.LocationDto
import com.hamza.rickandmorty.domain.model.LocationListing
import com.hamza.rickandmorty.util.Util
import com.hamza.rickandmorty.util.toLocalDateTime

fun LocationDto.toLocation(): List<LocationListing> {
    return this.results.map { location ->
        LocationListing(
            id = location.id,
            name = location.name,
            type = location.type,
            created = location.created.toLocalDateTime(),
            page = Util.getCurrentPage(this.info)
        )
    }
}

fun LocationListing.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        created = created,
        page = page
    )
}

fun LocationEntity.toLocationListing(): LocationListing {
    return LocationListing(
        id = id,
        name = name,
        type = type,
        created = created,
        page = page
    )
}