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
import com.hitesh.musicwiki.R
import com.hitesh.musicwiki.model.Album
import com.hitesh.musicwiki.ui.detailedgenre.DetailedGenreDirections
import com.squareup.picasso.Picasso

class AlbumsAdapter :
    RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
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
        val album = differ.currentList[position]
        if (album.image[0].text.length > 1) {
            Picasso.get().load(album.image[2].text).into(holder.albumImg)
        }
        holder.albumName.text = album.name
        holder.artistName.text = album.artist.name
        holder.albumImg.setOnClickListener {
            it.findNavController().navigate(
                DetailedGenreDirections.actionDetailedGenreToDetailedAlbumFragment2(
                    album.name,
                    album.artist.name
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val albumName: TextView = itemView.findViewById(R.id.item_name)
        val albumImg: ImageView = itemView.findViewById(R.id.img_item)
        val artistName: TextView = itemView.findViewById(R.id.item_artist_name)
    }
}