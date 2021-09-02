package com.example.animalcrossing.API

import com.example.animalcrossing.model.Art
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ArtAPI {
    @GET("nh/art")
    suspend fun getArtList(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String
    ) : Response<MutableList<Art>>
}