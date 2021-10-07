package ru.maggy.singlealbumapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.dto.Track

@Entity
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val file: String,
    var isPlaying: Boolean = false,
    val albumId: Int
) {
    fun toDto(): Track {
        return Track(
            id,
            file,
            isPlaying,
            albumId
        )
    }

    companion object {
        fun fromDto(dto: Track) =
            TrackEntity(
                dto.id,
                dto.file,
                dto.isPlaying,
                dto.albumId
            )
    }
}

fun List<TrackEntity>.toDto(): List<Track> = map(TrackEntity::toDto)
fun List<Track>.toEntity(): List<TrackEntity> = map(TrackEntity::fromDto)