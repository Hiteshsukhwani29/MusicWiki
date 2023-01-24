package com.hitesh.musicwiki.repository

import com.hitesh.musicwiki.api.RetrofitInstance
import com.hitesh.musicwiki.model.Albums
import com.hitesh.musicwiki.model.TagDetails
import com.hitesh.musicwiki.model.Tags
import retrofit2.Response

class MusicRepository {
    suspend fun getTopTags(): Response<Tags> {
        return RetrofitInstance.api.getTopTags()
    }
    suspend fun getTagDetails(tag: String): Response<TagDetails> {
        return RetrofitInstance.api.getTagDetails(tag)
    }
    suspend fun getTopAlbums(tag: String): Response<Albums> {
        return RetrofitInstance.api.getTopAlbums(tag)
    }
}