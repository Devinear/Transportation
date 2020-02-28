package com.custom.transportation.repository.mapper

interface Mapper<D, E> {
    //fun fromBookmark(bookmark : E) : D
    fun toBookmark(data : D) : E
}