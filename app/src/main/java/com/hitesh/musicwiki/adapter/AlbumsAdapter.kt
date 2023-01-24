package com.hitesh.musicwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hitesh.musicwiki.R
import com.hitesh.musicwiki.model.Album
import com.hitesh.musicwiki.model.Albums
import com.hitesh.musicwiki.model.Tag
import com.hitesh.musicwiki.ui.genre.GenreFragment
import com.hitesh.musicwiki.ui.genre.GenreFragmentDirections

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
            .inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = differ.currentList[position]
        holder.genre.text = genre.name
        holder.genre.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val genre: MaterialButton = itemView.findViewById(R.id.itemButton)
    }
}