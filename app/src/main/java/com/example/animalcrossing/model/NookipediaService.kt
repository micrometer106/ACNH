package com.example.animalcrossing.model

import com.example.animalcrossing.API.ArtAPI
import com.example.animalcrossing.API.BugsAPI
import com.example.animalcrossing.API.FishAPI
import com.example.animalcrossing.API.SeaCreaturesAPI
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

    fun getBugService() : BugsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BugsAPI::class.java)
    }

    fun getSeaCreatureService() : SeaCreaturesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SeaCreaturesAPI::class.java)
    }

    fun getArtService() : ArtAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtAPI::class.java)
    }
}