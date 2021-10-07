package ru.maggy.singlealbumapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.maggy.singlealbumapp.api.AlbumApi
import ru.maggy.singlealbumapp.dao.AlbumDao
import ru.maggy.singlealbumapp.dao.TracksDao
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.dto.Track
import ru.maggy.singlealbumapp.entity.TrackEntity
import ru.maggy.singlealbumapp.entity.toDto
import ru.maggy.singlealbumapp.entity.toEntity
import ru.maggy.singlealbumapp.exeptions.ApiException
import ru.maggy.singlealbumapp.exeptions.NetworkException
import ru.maggy.singlealbumapp.exeptions.UnknownException
import java.io.IOException
import java.lang.Exception

class TrackRepositoryImpl(private val tracksDao: TracksDao, private val albumDao: AlbumDao) : TrackRepository {

    override val data = tracksDao.getTracks().map(List<TrackEntity>::toDto)

    override val album = albumDao.getDataAlbum()

    override suspend fun getTracks() {
        try {
             tracksDao.getTracks()
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
    override suspend fun getAlbum() {
        try {
            albumDao.getDataAlbum()
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

    override suspend fun isPlaying(id: Int) {
        try {
            tracksDao.isPlaying(id)
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
}

/*
override suspend fun getTracks() {
    try {
        val response = AlbumApi.apiService.getAlbum()
        if (!response.isSuccessful) {
            throw ApiException(response.code(), response.message())
        }
        val body = response.body() ?: throw  ApiException(response.code(), response.message())
        tracksDao.insertTracks(body.tracks.toEntity())
    } catch (e: IOException) {
        throw NetworkException
    } catch (e: Exception) {
        throw UnknownException
    }
}*/
