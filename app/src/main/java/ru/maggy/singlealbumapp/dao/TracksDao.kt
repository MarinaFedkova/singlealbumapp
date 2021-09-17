package ru.maggy.singlealbumapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.maggy.singlealbumapp.entity.TrackEntity

@Dao
interface TracksDao {
    @Query("SELECT * FROM TrackEntity ORDER BY id ASC")
    fun getTracks(): LiveData<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(tracks: List<TrackEntity>)

    @Query(
        """
        UPDATE TrackEntity SET
        isPlaying = CASE WHEN isPlaying THEN 0 ELSE 1 END
        WHERE id = :id
    """
    )
    suspend fun isPlaying(id: Int)
}