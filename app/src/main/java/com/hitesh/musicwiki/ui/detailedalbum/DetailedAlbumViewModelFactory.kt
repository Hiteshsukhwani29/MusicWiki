package com.hitesh.musicwiki.ui.detailedalbum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hitesh.musicwiki.repository.MusicRepository

class DetailedAlbumViewModelFactory(private val musicRepository: MusicRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedAlbumViewModel::class.java)) {
            return DetailedAlbumViewModel(musicRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}