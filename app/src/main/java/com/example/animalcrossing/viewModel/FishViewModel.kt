package com.example.animalcrossing.viewModel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.animalcrossing.data.Fish
import com.example.animalcrossing.data.NookipediaService
import com.example.animalcrossing.data.repository.FishRepository
import com.example.animalcrossing.model.FishModel
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

class FishViewModel internal constructor(
    fishRepository: FishRepository,
    apiKey: String,
    apiVersion: String
) : CategoryViewModel() {

    companion object{
        private lateinit var instance: FishViewModel

//        @MainThread
//        fun getInstance(): FishViewModel{
//            instance = if(::instance.isInitialized) instance else FishViewModel()
//            return instance
//        }
    }

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val fishService = NookipediaService().getFishService()
    private var fishModel: FishModel? = null

    val fishList = MutableLiveData<MutableList<Fish>>()

    init {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val data = fishRepository.getFishList(apiKey, apiVersion)
            withContext(Dispatchers.Main) {
                LogUtils.d("TTT data = ${data.first().size}")
                fishList.value = data.first().toMutableList()
            }
        }
    }

    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
//        if (fishList.value == null) {
//            fetchFishList(apiKey, apiVersion)
//        }
    }

    private fun fetchFishList(apiKey: String, apiVersion: String) {
        if (fishModel == null) {
            fishModel = FishModel(apiKey, apiVersion, callback = {
//                fishList.value = it
                loading.value = false
            })
            fishModel?.getFishList()
        }
//        loading.value = true
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            val response = fishService.getFishList(
//                    apiKey,
//                    apiVersion
//            )
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    fishList.value = response.body()
//                } else {
//                    onError("Error : ${response.message()}, ${response.body()}")
//                }
//                loading.value = false
//            }
//        }
    }

    private fun onError(message: String) {
        LogUtils.w(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}