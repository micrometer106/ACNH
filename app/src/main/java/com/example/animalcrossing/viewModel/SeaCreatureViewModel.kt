package com.example.animalcrossing.viewModel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.example.animalcrossing.model.NookipediaService
import com.example.animalcrossing.model.SeaCreature
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*

class SeaCreatureViewModel : CreaturesViewModel() {

    companion object{
        private lateinit var instance: SeaCreatureViewModel

        @MainThread
        fun getInstance(): SeaCreatureViewModel{
            instance = if(::instance.isInitialized) instance else SeaCreatureViewModel()
            return instance
        }
    }

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val seaCreatureService = NookipediaService().getSeaCreatureService()

    val seaCreatureList = MutableLiveData<MutableList<SeaCreature>>()
    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
        if (seaCreatureList.value == null) {
            fetchSeaCreatureList(apiKey, apiVersion)
        }
    }

    private fun fetchSeaCreatureList(apiKey: String, apiVersion: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = seaCreatureService.getSeaCreatureList(
                apiKey,
                apiVersion
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    seaCreatureList.value = response.body()
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