package com.example.animalcrossing.data.repository

import com.example.animalcrossing.data.Fish
import com.example.animalcrossing.data.NookipediaService
import com.example.animalcrossing.data.database.FishDao

class FishRepository constructor(
    private val nookipediaService: NookipediaService,
    private val fishDao: FishDao,
    private val apiKey: String,
    private val apiVersion: String
) {
    fun getFishList() : MutableList<Fish> {
        refreshFish()
        return fishDao.getAll()
    }

    private suspend fun refreshFish() {
        val fishListExist = fishDao.getAll()
        if (fishListExist.size == 0) {
            val response = nookipediaService.getFishService().getFishList(
                apiKey, apiVersion
            )
            fishDao.save(response.body()!!)
        }
    }
}