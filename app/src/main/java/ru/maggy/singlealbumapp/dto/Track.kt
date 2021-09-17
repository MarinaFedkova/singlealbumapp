package ru.maggy.singlealbumapp.dto

data class Track(
    val id: Int,
    val file: String,
    val isPlaying: Boolean = false

)