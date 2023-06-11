package com.leonel.kitsuapp.network

import com.leonel.kitsuapp.model.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class service @Inject constructor(private val api:apiClient) {

    suspend fun getAnimes(): List<Anime> {
        return withContext(Dispatchers.IO) {
            val response = api.getAnimes()
            response.body()?.data ?: emptyList()
        }
    }

    suspend fun getAnimeById(id: String): Anime {
        return withContext(Dispatchers.IO) {
            val response = api.getAnimeById(id)
            response.body()!!.data
        }
    }
}