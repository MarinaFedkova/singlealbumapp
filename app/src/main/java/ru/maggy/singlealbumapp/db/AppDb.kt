package ru.maggy.singlealbumapp.db

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.maggy.singlealbumapp.dao.AlbumDao
import ru.maggy.singlealbumapp.dao.TracksDao
import ru.maggy.singlealbumapp.dto.Track
import ru.maggy.singlealbumapp.entity.AlbumEntity
import ru.maggy.singlealbumapp.entity.TrackEntity

@Database(
    entities = [TrackEntity::class, AlbumEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun trackDao(): TracksDao
    abstract fun albumdao(): AlbumDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .build()
    }
}

class Converters {
    @TypeConverter
    fun fromListTrackToString(value: List<Track>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToListTrack(value: String): List<Track> {
        val type = object : TypeToken<Track>() {}.type
        val list = listOf(Gson().fromJson<Track>(value, type))
        return list
    }
}


