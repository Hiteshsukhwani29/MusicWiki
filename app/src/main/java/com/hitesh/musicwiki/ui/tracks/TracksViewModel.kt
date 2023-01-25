package com.hitesh.musicwiki.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.Tracks
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class TracksViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private var _response: MutableLiveData<Response<Tracks>> = MutableLiveData()
    val response: LiveData<Response<Tracks>>
        get() = _response

    suspend fun getTopTracks(tag: String) {
        _response.postValue(musicRepository.getTopTracks(tag))
    }

}