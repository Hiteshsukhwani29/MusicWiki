package com.hitesh.musicwiki.api

import com.hitesh.musicwiki.model.*
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

    @GET("2.0")
    suspend fun getTopArtists(
        @Query("tag") tag: String = "disco",
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "tag.gettopartists",
        @Query("format") format: String = "json",
    ): Response<Artists>

    @GET("2.0")
    suspend fun getTopTracks(
        @Query("tag") tag: String = "disco",
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "tag.gettoptracks",
        @Query("format") format: String = "json",
    ): Response<Tracks>

    @GET("2.0")
    suspend fun getAlbumDetails(
        @Query("album") albumName: String,
        @Query("artist") artistName: String,
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "album.getinfo",
        @Query("format") format: String = "json",
    ): Response<DetailedAlbum>

    @GET("2.0")
    suspend fun getArtistDetails(
        @Query("artist") artistName: String,
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "artist.getinfo",
        @Query("format") format: String = "json",
    ): Response<ArtistDetails>

    @GET("2.0")
    suspend fun getArtistTopAlbums(
        @Query("artist") artistName: String,
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "artist.getTopAlbums",
        @Query("format") format: String = "json",
    ): Response<ArtistTopAlbums>

    @GET("2.0")
    suspend fun getArtistTopTracks(
        @Query("artist") artistName: String,
        @Query("api_key") apikey: String = API_KEY,
        @Query("method") method: String = "artist.getTopTracks",
        @Query("format") format: String = "json",
    ): Response<ArtistTopTracks>

}