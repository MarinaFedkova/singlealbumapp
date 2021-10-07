package ru.maggy.singlealbumapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.dto.Track

@Entity
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val idAlbum: Int,
    val title: String,
    val subtitle: String,
    val artist: String,
    val published: String,
    val genre: String,
    @TypeConverters
    val tracks: List<Track>

) {
    fun toDto(): Album {
        return Album(
            idAlbum,
            title,
            subtitle,
            artist,
            published,
            genre,
            tracks,
        )
    }

    companion object {
        fun fromDto(dto: Album) =
            AlbumEntity(
                dto.idAlbum,
                dto.title,
                dto.subtitle,
                dto.artist,
                dto.published,
                dto.genre,
                dto.tracks,
            )
    }
}
