package ru.maggy.singlealbumapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maggy.singlealbumapp.dto.Track

@Entity
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val file: String,
    val isPlaying: Boolean = false,
) {
    fun toDto(): Track {
        return Track(
            id,
            file,
            isPlaying,
        )
    }

    companion object {
        fun fromDto(dto: Track) =
            TrackEntity(
                dto.id,
                dto.file,
                dto.isPlaying,
            )
    }
}

fun List<TrackEntity>.toDto(): List<Track> = map(TrackEntity::toDto)
fun List<Track>.toEntity(): List<TrackEntity> = map(TrackEntity::fromDto)