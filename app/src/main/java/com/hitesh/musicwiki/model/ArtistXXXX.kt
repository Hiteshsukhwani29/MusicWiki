package com.hitesh.musicwiki.model

data class ArtistXXXX(
    val bio: Bio,
    val image: List<ImageXXXX>,
    val mbid: String,
    val name: String,
    val ontour: String,
    val similar: Similar,
    val stats: Stats,
    val streamable: String,
    val tags: TagsXX,
    val url: String
)