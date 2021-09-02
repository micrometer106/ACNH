package com.example.animalcrossing.API

import com.example.animalcrossing.model.Fish
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface FishAPI {
    @GET("nh/fish")
    suspend fun getFishList(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String
    ) : Response<MutableList<Fish>>
}