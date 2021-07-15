package com.example.animalcrossing.API

import com.example.animalcrossing.model.SeaCreature
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SeaCreaturesAPI {
    @GET("sea")
    suspend fun getFishList(
            @Header("X-API-KEY") apiKey: String,
            @Header("Accept-Version") version: String
    ) : Response<MutableList<SeaCreature>>
}