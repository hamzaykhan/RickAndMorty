package com.hamza.rickandmorty.util

import com.hamza.rickandmorty.data.remote.dto.InfoDto

object Util {
    fun getCurrentPage(infoDto: InfoDto): Int {
        val previousPage = if (infoDto.prev != null) infoDto.prev.last().digitToInt() else 1
        val nextPage = infoDto.next.last().digitToInt()

        return nextPage - previousPage
    }


}