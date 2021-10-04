package com.example.animalcrossing.API

import com.example.animalcrossing.data.Villager
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VillagersAPI {
    @GET("villagers")
    suspend fun getVillagersList(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String
    ) : Response<Villager>

    @GET("villagers")
    suspend fun getVillagerByBirth(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String,
        @Query("birthmonth") birthmonth: Int,
        @Query("birthday") birthday: Int
    ) : Response<MutableList<Villager>>
}