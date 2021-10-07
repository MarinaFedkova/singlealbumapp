package ru.maggy.singlealbumapp.dto

data class Album(
    val idAlbum: Int = 0,
    val title: String = "",
    val subtitle: String = "",
    val artist: String = "",
    val published: String = "",
    val genre: String = "",
    val tracks: List<Track> = emptyList()
)