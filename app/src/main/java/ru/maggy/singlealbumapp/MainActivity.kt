package ru.maggy.singlealbumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.maggy.singlealbumapp.BuildConfig.BASE_URL
import ru.maggy.singlealbumapp.databinding.ActivityMainBinding
import ru.maggy.singlealbumapp.dto.Track
import ru.maggy.singlealbumapp.viewModel.TracksViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: TracksViewModel by viewModels()
    private val mediaObserver = MediaLifecycleObserver()
    private val tracks: List<Track> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(mediaObserver)

        val adapter = TracksAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                viewModel.isPlaying(track.id)
                mediaObserver.apply {
                    player?.setDataSource(BASE_URL + track.file)
                }.play()
            }
        })

        binding.tracks.adapter = adapter

        viewModel.album.observe(this) {
            binding.apply {
                albumName.text = it.title
                artistName.text = it.artist
                published.text = it.published
                genre.text = it.genre
            }
        }

        viewModel.dataTracks.observe(this) { tracks ->
            adapter.submitList(tracks)
        }
    }

}