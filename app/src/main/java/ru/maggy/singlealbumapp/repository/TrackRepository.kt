package ru.maggy.singlealbumapp.repository

import androidx.lifecycle.LiveData
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.dto.Track
import ru.maggy.singlealbumapp.entity.TrackEntity

interface TrackRepository {
    val data: LiveData<List<Track>>
    val album: LiveData<Album>
    suspend fun getTracks()
    suspend fun getAlbum()
    suspend fun isPlaying(id: Int)
}