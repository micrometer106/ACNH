package com.example.animalcrossing.API

import com.example.animalcrossing.data.Fish
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FishAPI {
    @GET("nh/fish")
    suspend fun getFishList(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String
    ) : Response<MutableList<Fish>>
}