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
import com.hitesh.musicwiki.model.Album
import com.hitesh.musicwiki.model.Albums
import com.hitesh.musicwiki.model.ArtistX
import com.hitesh.musicwiki.model.Tag
import com.hitesh.musicwiki.ui.detailedgenre.DetailedGenreDirections
import com.hitesh.musicwiki.ui.genre.GenreFragment
import com.hitesh.musicwiki.ui.genre.GenreFragmentDirections
import com.squareup.picasso.Picasso

class ArtistsAdapter :
    RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<ArtistX>() {
        override fun areItemsTheSame(oldItem: ArtistX, newItem: ArtistX): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ArtistX, newItem: ArtistX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = differ.currentList[position]
        if (artist.image[0].text.length > 1) {
            Picasso.get().load(artist.image[2].text).into(holder.artistImg)
        }
        holder.artistName.text = artist.name
        holder.artistImg.setOnClickListener {
            it.findNavController().navigate(DetailedGenreDirections.actionDetailedGenreToDetailedArtistFragment(artist.name))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val artistImg: ImageView = itemView.findViewById(R.id.img_artist)
    }
}