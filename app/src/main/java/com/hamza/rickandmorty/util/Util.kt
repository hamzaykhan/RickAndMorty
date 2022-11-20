package com.hamza.rickandmorty.util
import com.hamza.rickandmorty.data.remote.dto.InfoDto

object Util {
    fun getCurrentPage(infoDto: InfoDto): Int {
        val previousPage = if (infoDto.prev != null) infoDto.prev.substringAfter("page=").substringBefore("&").toInt() else 1
        val nextPage = infoDto.next.substringAfter("page=").substringBefore("&").toInt()

        return nextPage - previousPage
    }
}