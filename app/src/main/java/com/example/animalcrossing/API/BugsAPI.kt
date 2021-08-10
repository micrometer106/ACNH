package com.example.animalcrossing.API

import com.example.animalcrossing.model.Bug
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BugsAPI {
    @GET("bugs")
    suspend fun getBugList(
            @Header("X-API-KEY") apiKey: String,
            @Header("Accept-Version") version: String
    ) : Response<MutableList<Bug>>
}