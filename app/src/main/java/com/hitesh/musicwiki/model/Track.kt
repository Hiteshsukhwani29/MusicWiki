package com.hitesh.musicwiki.model

data class Track(
    val artist: ArtistXX,
    val duration: String,
    val image: List<ImageXX>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)