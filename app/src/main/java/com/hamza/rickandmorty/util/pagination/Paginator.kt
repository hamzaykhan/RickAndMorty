package com.hamza.rickandmorty.util.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun resetItems()
}