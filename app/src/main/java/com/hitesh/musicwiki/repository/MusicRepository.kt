package com.hitesh.musicwiki.repository

import com.hitesh.musicwiki.api.RetrofitInstance
import com.hitesh.musicwiki.model.*
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

    suspend fun getTopArtists(tag: String): Response<Artists> {
        return RetrofitInstance.api.getTopArtists(tag)
    }

    suspend fun getTopTracks(tag: String): Response<Tracks> {
        return RetrofitInstance.api.getTopTracks(tag)
    }

    suspend fun getAlbumDetails(albumName: String, artistName: String): Response<DetailedAlbum> {
        return RetrofitInstance.api.getAlbumDetails(albumName, artistName)
    }

    suspend fun getArtistDetails(artistName: String): Response<ArtistDetails> {
        return RetrofitInstance.api.getArtistDetails(artistName)
    }

    suspend fun getArtistTopAlbums(artistName: String): Response<ArtistTopAlbums> {
        return RetrofitInstance.api.getArtistTopAlbums(artistName)
    }

    suspend fun getArtistTopTracks(artistName: String): Response<ArtistTopTracks> {
        return RetrofitInstance.api.getArtistTopTracks(artistName)
    }
}