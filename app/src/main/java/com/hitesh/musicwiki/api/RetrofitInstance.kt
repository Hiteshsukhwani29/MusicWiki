package com.hitesh.musicwiki.api

import com.hitesh.musicwiki.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val client = OkHttpClient.Builder().build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: LastfmAPI by lazy {
            retrofit.create(LastfmAPI::class.java)
        }

    }
}