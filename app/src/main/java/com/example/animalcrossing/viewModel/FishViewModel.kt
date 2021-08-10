package com.example.animalcrossing.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.animalcrossing.model.Fish
import com.example.animalcrossing.model.NookipediaService
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*

class FishViewModel : CreaturesViewModel() {

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val fishService = NookipediaService().getFishService()

    val fishList = MutableLiveData<MutableList<Fish>>()
    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
        fetchFishList(apiKey, apiVersion)
    }

    private fun fetchFishList(apiKey: String, apiVersion: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = fishService.getFishList(
                    apiKey,
                    apiVersion
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    fishList.value = response.body()
                } else {
                    onError("Error : ${response.message()}, ${response.body()}")
                }
                loading.value = false
            }
        }
    }

    private fun onError(message: String) {
        LogUtils.w(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}