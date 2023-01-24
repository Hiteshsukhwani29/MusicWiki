package com.hitesh.musicwiki.api

import com.hitesh.musicwiki.model.Albums
import com.hitesh.musicwiki.model.TagDetails
import com.hitesh.musicwiki.model.Tags
import com.hitesh.musicwiki.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastfmAPI {

    @GET("2.0")
    suspend fun getTopTags(
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "chart.gettoptags",
        @Query("format") format: String = "json"
    ): Response<Tags>

    @GET("2.0")
    suspend fun getTagDetails(
        @Query("tag") tag: String = "disco",
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "tag.getinfo",
        @Query("format") format: String = "json",

        ): Response<TagDetails>

    @GET("2.0")
    suspend fun getTopAlbums(
        @Query("tag") tag: String = "disco",
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "tag.gettopalbums",
        @Query("format") format: String = "json",
        ): Response<Albums>

}