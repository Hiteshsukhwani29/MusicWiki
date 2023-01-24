package com.hitesh.musicwiki.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.Albums
import com.hitesh.musicwiki.model.TagDetails
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class AlbumsViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private var _response: MutableLiveData<Response<Albums>> = MutableLiveData()
    val response: LiveData<Response<Albums>>
        get() = _response

    suspend fun getTopAlbums(tag: String) {
        _response?.postValue(musicRepository.getTopAlbums(tag))
    }

    init {
//        getTopTags()
    }

}