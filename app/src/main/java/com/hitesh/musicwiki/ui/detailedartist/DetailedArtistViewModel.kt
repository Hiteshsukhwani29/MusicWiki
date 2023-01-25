package com.hitesh.musicwiki.ui.detailedartist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.*
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class DetailedArtistViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private var _response: MutableLiveData<Response<ArtistDetails>> = MutableLiveData()
    val response: LiveData<Response<ArtistDetails>>
        get() = _response

    private var _topAlbums: MutableLiveData<Response<ArtistTopAlbums>> = MutableLiveData()
    val topAlbums: LiveData<Response<ArtistTopAlbums>>
        get() = _topAlbums

    private var _topTracks: MutableLiveData<Response<ArtistTopTracks>> = MutableLiveData()
    val topTracks: LiveData<Response<ArtistTopTracks>>
        get() = _topTracks

    suspend fun getArtistDetails(artistName: String) {
        _response?.postValue(musicRepository.getArtistDetails(artistName))
    }

    suspend fun getArtistTopAlbums(artistName: String) {
        _topAlbums?.postValue(musicRepository.getArtistTopAlbums(artistName))
    }

    suspend fun getArtistTopTracks(artistName: String) {
        _topTracks?.postValue(musicRepository.getArtistTopTracks(artistName))
    }

    init {
//        getTopTags()
    }

}