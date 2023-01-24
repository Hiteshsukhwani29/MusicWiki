package com.hitesh.musicwiki.ui.detailedgenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hitesh.musicwiki.repository.MusicRepository

class DetailedGenreViewModelFactory(val musicRepository: MusicRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedGenreViewModel::class.java)) {
            return DetailedGenreViewModel(musicRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}