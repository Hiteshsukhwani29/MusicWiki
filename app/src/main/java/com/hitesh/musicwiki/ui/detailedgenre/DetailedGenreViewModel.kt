package com.hitesh.musicwiki.ui.detailedgenre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.TagDetails
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class DetailedGenreViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private var _response: MutableLiveData<Response<TagDetails>> = MutableLiveData()
    val response: LiveData<Response<TagDetails>>
        get() = _response

    suspend fun getTagDetails(tag: String) {
        _response.postValue(musicRepository.getTagDetails(tag))
    }

}