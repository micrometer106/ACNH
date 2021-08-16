package com.example.animalcrossing.viewModel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.example.animalcrossing.model.Art
import com.example.animalcrossing.model.NookipediaService
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*

class ArtViewModel : CategoryViewModel() {

    companion object{
        private lateinit var instance: ArtViewModel

        @MainThread
        fun getInstance(): ArtViewModel{
            instance = if(::instance.isInitialized) instance else ArtViewModel()
            return instance
        }
    }

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val artService = NookipediaService().getArtService()

    val artList = MutableLiveData<MutableList<Art>>()
    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
        if (artList.value == null) {
            fetchArtList(apiKey, apiVersion)
        }
    }

    private fun fetchArtList(apiKey: String, apiVersion: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = artService.getArtList(
                apiKey,
                apiVersion
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    artList.value = response.body()
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