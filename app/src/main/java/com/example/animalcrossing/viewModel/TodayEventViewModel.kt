package com.example.animalcrossing.viewModel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.example.animalcrossing.data.Event
import com.example.animalcrossing.data.NookipediaService
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*

class TodayEventViewModel : CategoryViewModel() {

    companion object{
        private lateinit var instance: TodayEventViewModel

        @MainThread
        fun getInstance(): TodayEventViewModel{
            instance = if(::instance.isInitialized) instance else TodayEventViewModel()
            return instance
        }
    }

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val eventService = NookipediaService().getEventService()

    val event = MutableLiveData<MutableList<Event>>()
    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
        if (event.value == null) {
            fetchSingleEvent(apiKey, apiVersion)
        }
    }

    private fun fetchSingleEvent(apiKey: String, apiVersion: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = eventService.getEvent(
                apiKey,
                apiVersion,
                "today"
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    event.value = response.body()
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