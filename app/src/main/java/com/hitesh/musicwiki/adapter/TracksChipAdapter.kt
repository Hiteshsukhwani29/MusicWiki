package com.hitesh.musicwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.musicwiki.R
import com.hitesh.musicwiki.model.TrackXX
import com.squareup.picasso.Picasso

class TracksChipAdapter :
    RecyclerView.Adapter<TracksChipAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<TrackXX>() {
        override fun areItemsTheSame(oldItem: TrackXX, newItem: TrackXX): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: TrackXX, newItem: TrackXX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = differ.currentList[position]
        if (track.image[0].text.length > 1) {
            Picasso.get().load(track.image[2].text).into(holder.trackImg)
        }
        holder.trackName.text = track.name
        holder.artistName.text = track.artist.name
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val trackName: TextView = itemView.findViewById(R.id.album_name)
        val trackImg: ImageView = itemView.findViewById(R.id.img_album)
        val artistName: TextView = itemView.findViewById(R.id.album_artist)
    }
}