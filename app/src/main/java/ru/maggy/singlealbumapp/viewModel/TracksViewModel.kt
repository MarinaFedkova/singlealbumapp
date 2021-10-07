package ru.maggy.singlealbumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maggy.singlealbumapp.db.AppDb
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.dto.Track
import ru.maggy.singlealbumapp.repository.TrackRepository
import ru.maggy.singlealbumapp.repository.TrackRepositoryImpl

private var emptyAlbum = Album(0, "", "", "", "", "", emptyList())
private var emptyTrack = Track(0, "", false, 0)

class TracksViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrackRepository = TrackRepositoryImpl(
        AppDb.getInstance(context = application).trackDao(),
        AppDb.getInstance(context = application).albumdao()
    )

    val dataTracks = repository.data

    private val _tracks = MutableLiveData(emptyTrack)
    val tracks: LiveData<Track>
        get() = _tracks

    val dataAlbum = repository.album

    private val _album = MutableLiveData(emptyAlbum)
    val album: LiveData<Album>
        get() = _album

   /* init {
        loadAlbum()
    }

    private fun loadAlbum() = viewModelScope.launch {
        try {
           _album.value = repository.getAlbum()
            repository.getTracks()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    fun isPlaying(id: Int) = viewModelScope.launch {
        try {
            repository.isPlaying(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}