package com.hitesh.musicwiki.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitesh.musicwiki.model.Tags
import com.hitesh.musicwiki.repository.MusicRepository
import retrofit2.Response

class GenreViewModel(val musicRepository: MusicRepository) : ViewModel() {
    var _genreResponse: MutableLiveData<Response<Tags>> = MutableLiveData()
//    val genreResponse: LiveData<Response<Tags>>
//        get() = _genreResponse

    suspend fun getTopTags() {
        _genreResponse?.postValue(musicRepository.getTopTags())
    }

    init {
//        getTopTags()
    }

}