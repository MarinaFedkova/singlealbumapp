package ru.maggy.singlealbumapp.dto

data class Track(
    val id: Int,
    val file: String,
    var isPlaying: Boolean = false,
    val albumId: Int

)