package com.example.animalcrossing.model

import com.example.animalcrossing.data.Fish
import com.example.animalcrossing.data.NookipediaService
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*

class FishModel(
    private val apiKey: String,
    private val apiVersion: String,
    private val callback: (fishList: MutableList<Fish>) -> Unit
) {
    private val job = Job()
    private val fishService = NookipediaService().getFishService()

    fun getFishList() {
        CoroutineScope(Dispatchers.IO + job).launch {
            val response = fishService.getFishList(
                apiKey,
                apiVersion
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    callback(response.body() ?: mutableListOf())
                } else {
                    onError("Error : ${response.message()}, ${response.body()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        LogUtils.w(message)
    }

    private fun onCleared() {
        job.cancel()
    }
}