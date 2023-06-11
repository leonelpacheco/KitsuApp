package com.leonel.kitsuapp.repository

import com.leonel.kitsuapp.model.Anime
import com.leonel.kitsuapp.network.service
import javax.inject.Inject

class animeRepository @Inject constructor(private val api: service) {

    suspend fun getAnimes(): List<Anime> {
        return api.getAnimes()
    }

    suspend fun getAnimeById(id: String): Anime {
        return api.getAnimeById(id)
    }

}