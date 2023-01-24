package com.hitesh.musicwiki.ui.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.Albums
import com.hitesh.musicwiki.model.Artists
import com.hitesh.musicwiki.model.TagDetails
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class ArtistsViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private var _response: MutableLiveData<Response<Artists>> = MutableLiveData()
    val response: LiveData<Response<Artists>>
        get() = _response

    suspend fun getTopArtists(tag: String) {
        _response?.postValue(musicRepository.getTopArtists(tag))
    }

    init {
//        getTopTags()
    }

}