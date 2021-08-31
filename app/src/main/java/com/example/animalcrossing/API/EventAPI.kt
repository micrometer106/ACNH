package com.example.animalcrossing.API

import com.example.animalcrossing.model.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EventAPI {
    @GET("events")
    suspend fun getEventList(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String
    ) : Response<MutableList<Event>>

    @GET("events")
    suspend fun getEvent(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String,
        @Query("date") date: String
    ) : Response<MutableList<Event>>
}