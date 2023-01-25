package com.hitesh.musicwiki.ui.detailedalbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.DetailedAlbum
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class DetailedAlbumViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private var _response: MutableLiveData<Response<DetailedAlbum>> = MutableLiveData()
    val response: LiveData<Response<DetailedAlbum>>
        get() = _response

    suspend fun getAlbumDetails(albumName: String, artistName: String) {
        _response.postValue(musicRepository.getAlbumDetails(albumName, artistName))
    }

}