package ru.maggy.singlealbumapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.maggy.singlealbumapp.api.AlbumApi
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

class TrackRepositoryImpl(private val dao: TracksDao) : TrackRepository {

    override val data = dao.getTracks().map(List<TrackEntity>::toDto)

    override suspend fun getAlbum(): Album {
        try {
            val response = AlbumApi.apiService.getAlbum()
            if (!response.isSuccessful) {
                throw ApiException(response.code(), response.message())
            }
            val body = response.body() ?: throw  ApiException(response.code(), response.message())
            dao.insertTracks(body.tracks.toEntity())
            return body
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

    override suspend fun isPlaying(id: Int) {
        try {
            dao.isPlaying(id)
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
}