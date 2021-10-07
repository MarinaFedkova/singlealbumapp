package ru.maggy.singlealbumapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.entity.AlbumEntity

@Dao
interface AlbumDao {
    @Query("SELECT * FROM AlbumEntity ORDER BY idAlbum ASC")
    fun getDataAlbum(): LiveData<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataAlbum(album: AlbumEntity)
}


