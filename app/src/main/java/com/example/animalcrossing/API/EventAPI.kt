package com.example.animalcrossing.API

import com.example.animalcrossing.data.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EventAPI {
    @GET("nh/events")
    suspend fun getEventList(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String
    ) : Response<MutableList<Event>>

    @GET("nh/events")
    suspend fun getEvent(
        @Header("X-API-KEY") apiKey: String,
        @Header("Accept-Version") version: String,
        @Query("date") date: String
    ) : Response<MutableList<Event>>
}