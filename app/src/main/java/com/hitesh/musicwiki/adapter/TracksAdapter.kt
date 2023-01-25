package com.hitesh.musicwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hitesh.musicwiki.R
import com.hitesh.musicwiki.model.*
import com.hitesh.musicwiki.ui.genre.GenreFragment
import com.hitesh.musicwiki.ui.genre.GenreFragmentDirections
import com.squareup.picasso.Picasso

class TracksAdapter :
    RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
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
        val trackName = itemView.findViewById<TextView>(R.id.item_name)
        val trackImg = itemView.findViewById<ImageView>(R.id.img_item)
        val artistName = itemView.findViewById<TextView>(R.id.item_artist_name)
    }
}