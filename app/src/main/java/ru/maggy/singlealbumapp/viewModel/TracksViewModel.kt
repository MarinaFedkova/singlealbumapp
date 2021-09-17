package ru.maggy.singlealbumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maggy.singlealbumapp.db.AppDb
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.repository.TrackRepository
import ru.maggy.singlealbumapp.repository.TrackRepositoryImpl

private var emptyAlbum = Album(0, "", "", "", "", "", emptyList())

class TracksViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TrackRepository = TrackRepositoryImpl(AppDb.getInstance(context = application).trackDao())

    val data = repository.data

     private val _album = MutableLiveData(emptyAlbum)
    val album: LiveData<Album>
    get() = _album

    init {
        loadTracks()
    }

    private fun loadTracks() = viewModelScope.launch {
        try {
            _album.value = repository.getAlbum()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isPlaying(id: Int) = viewModelScope.launch {
        try {
            repository.isPlaying(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}