package com.hitesh.musicwiki.ui.tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hitesh.musicwiki.repository.MusicRepository

class TracksViewModelFactory(private val musicRepository: MusicRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TracksViewModel::class.java)) {
            return TracksViewModel(musicRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}