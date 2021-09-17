package ru.maggy.singlealbumapp.repository

import androidx.lifecycle.LiveData
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.dto.Track

interface TrackRepository {
    val data: LiveData<List<Track>>
    suspend fun getAlbum(): Album
    suspend fun isPlaying(id: Int)
}