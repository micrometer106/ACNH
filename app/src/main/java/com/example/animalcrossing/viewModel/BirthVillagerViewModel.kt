package com.example.animalcrossing.viewModel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.example.animalcrossing.model.NookipediaService
import com.example.animalcrossing.model.Villager
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*
import java.util.*

class BirthVillagerViewModel : CategoryViewModel() {

    companion object{
        private lateinit var instance: BirthVillagerViewModel

        @MainThread
        fun getInstance(): BirthVillagerViewModel{
            instance = if(::instance.isInitialized) instance else BirthVillagerViewModel()
            return instance
        }
    }

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val villagerService = NookipediaService().getVillagerService()

    val villager = MutableLiveData<MutableList<Villager>>()
    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
        if (villager.value == null) {
            fetchSingleEvent(apiKey, apiVersion)
        }
    }

    private fun fetchSingleEvent(apiKey: String, apiVersion: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            val response = villagerService.getVillagerByBirth(
                apiKey,
                apiVersion,
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    villager.value = response.body()
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