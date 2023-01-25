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
import com.hitesh.musicwiki.model.AlbumXX
import com.squareup.picasso.Picasso

class AlbumsChipAdapter :
    RecyclerView.Adapter<AlbumsChipAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<AlbumXX>() {
        override fun areItemsTheSame(oldItem: AlbumXX, newItem: AlbumXX): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: AlbumXX, newItem: AlbumXX): Boolean {
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
        val album = differ.currentList[position]
        if (album.image[0].text.length > 1) {
            Picasso.get().load(album.image[2].text).into(holder.albumImg)
        }
        holder.albumName.text = album.name
        holder.artistName.text = album.artist.name
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val albumName: TextView = itemView.findViewById(R.id.album_name)
        val albumImg: ImageView = itemView.findViewById(R.id.img_album)
        val artistName: TextView = itemView.findViewById(R.id.album_artist)
    }
}