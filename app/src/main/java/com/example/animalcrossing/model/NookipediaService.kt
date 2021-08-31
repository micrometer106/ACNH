package com.example.animalcrossing.model

import com.example.animalcrossing.API.*
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

    fun getEventService() : EventAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventAPI::class.java)
    }
}