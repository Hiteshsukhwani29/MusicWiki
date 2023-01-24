package com.hitesh.musicwiki.repository

import com.hitesh.musicwiki.api.RetrofitInstance
import com.hitesh.musicwiki.model.Tags
import retrofit2.Response

class MusicRepository {
    suspend fun getTopTags(): Response<Tags> {
        return RetrofitInstance.api.getTopTags()
    }
}