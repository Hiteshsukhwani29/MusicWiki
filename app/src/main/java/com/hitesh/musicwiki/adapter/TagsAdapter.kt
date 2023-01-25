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
import com.hitesh.musicwiki.model.Tag
import com.hitesh.musicwiki.ui.genre.GenreFragmentDirections

class TagsAdapter :
    RecyclerView.Adapter<TagsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
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
        val tag = differ.currentList[position]
        holder.tag.text = tag.name
        holder.tag.setOnClickListener {
            it.findNavController()
                .navigate(GenreFragmentDirections.actionGenreFragmentToDetailedGenre(tag.name))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tag: MaterialButton = itemView.findViewById(R.id.itemButton)
    }
}