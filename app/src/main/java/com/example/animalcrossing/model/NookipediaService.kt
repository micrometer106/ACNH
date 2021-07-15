package com.example.animalcrossing.model

import com.example.animalcrossing.API.FishAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NookipediaService {
    private val BASE_URL = "https://api.nookipedia.com/nh/"

    fun getFishService() : FishAPI {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FishAPI::class.java)
    }
}