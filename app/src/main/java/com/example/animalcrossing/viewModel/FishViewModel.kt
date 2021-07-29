package com.example.animalcrossing.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalcrossing.model.Fish
import com.example.animalcrossing.model.NookipediaService
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class FishViewModel : ViewModel() {

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val fishService = NookipediaService().getFishService()

    val fishList = MutableLiveData<MutableList<Fish>>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(apiKey: String, apiVersion: String) {
        fetchFishList(apiKey, apiVersion)
    }

    private fun fetchFishList(apiKey: String, apiVersion: String) {
        Log.d("FishViewModel", "TTT fetchFishList")
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
                    Log.d("FishViewModel", "TTT onError ${response.message()}, ${response.raw()}")
                    onError("Error : ${response.message()}, ${response.body()}")
                }
            }
        }
    }

    private fun onError(message: String) {
//        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}