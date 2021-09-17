package ru.maggy.singlealbumapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.maggy.singlealbumapp.databinding.TrackCardBinding
import ru.maggy.singlealbumapp.dto.Track


interface OnInteractionListener {
    fun onPlayOrPause(track: Track) {}
}

class TracksAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Track, TrackViewHolder>(TrackDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = TrackCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }

    }
}

class TrackViewHolder(
    private val binding: TrackCardBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track) {
        binding.apply {
            trackName.text = track.file
            if (track.isPlaying) {
                playButton.setImageResource(R.drawable.ic_pause_48)
            } else {
                playButton.setImageResource(R.drawable.ic_play_48)
            }

            playButton.setOnClickListener {
                onInteractionListener.onPlayOrPause(track)
            }
        }
    }
}

class TrackDiffCallBack : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}