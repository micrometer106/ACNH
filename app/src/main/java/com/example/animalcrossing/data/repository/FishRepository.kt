package com.example.animalcrossing.data.repository

import com.example.animalcrossing.data.Fish
import com.example.animalcrossing.data.NookipediaService
import com.example.animalcrossing.data.database.FishDao
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList

class FishRepository constructor(
    private val nookipediaService: NookipediaService,
    private val fishDao: FishDao
) {
    suspend fun getFishList(apiKey: String, apiVersion: String) : Flow<List<Fish>> {
        LogUtils.d("TTT getFishList")
        refreshFish(apiKey, apiVersion)
        return fishDao.getAll()
    }

    private suspend fun refreshFish(apiKey: String, apiVersion: String) {
        val fishListExist = fishDao.getAll().first()
        LogUtils.d("TTT fishExist = ${fishListExist.size}")
        if (fishListExist.isEmpty()) {
            val response = nookipediaService.getFishService().getFishList(
                apiKey, apiVersion
            )
            fishDao.save(response.body()!!)
        }
    }
}