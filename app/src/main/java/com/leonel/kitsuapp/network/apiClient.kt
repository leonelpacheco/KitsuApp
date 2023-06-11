package com.leonel.kitsuapp.network


import com.leonel.kitsuapp.model.responseAnime
import com.leonel.kitsuapp.model.responseAnimeById
import com.leonel.kitsuapp.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface apiClient {
    @Headers("Content-Type:application/vnd.api+json","Accept: application/vnd.api+json")
    @GET(Constant.ANIME)
    suspend fun getAnimes(): Response<responseAnime>

    @Headers("Content-Type:application/vnd.api+json","Accept: application/vnd.api+json")
    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: String): Response<responseAnimeById>

}