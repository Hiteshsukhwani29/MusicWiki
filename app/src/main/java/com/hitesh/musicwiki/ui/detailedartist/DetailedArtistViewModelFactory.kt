package com.hitesh.musicwiki.ui.detailedartist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hitesh.musicwiki.repository.MusicRepository

class DetailedArtistViewModelFactory(private val musicRepository: MusicRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedArtistViewModel::class.java)) {
            return DetailedArtistViewModel(musicRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}