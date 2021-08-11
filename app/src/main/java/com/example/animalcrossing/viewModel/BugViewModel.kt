package com.example.animalcrossing.viewModel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.example.animalcrossing.model.Bug
import com.example.animalcrossing.model.NookipediaService
import com.example.animalcrossing.utils.LogUtils
import kotlinx.coroutines.*

class BugViewModel : CreaturesViewModel() {

    companion object{
        private lateinit var instance: BugViewModel

        @MainThread
        fun getInstance(): BugViewModel{
            instance = if(::instance.isInitialized) instance else BugViewModel()
            return instance
        }
    }

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val bugService = NookipediaService().getBugService()

    val bugList = MutableLiveData<MutableList<Bug>>()
    private val loading = MutableLiveData<Boolean>()

    override fun refresh(apiKey: String, apiVersion: String) {
        if (bugList.value == null) {
            fetchBugList(apiKey, apiVersion)
        }
    }

    private fun fetchBugList(apiKey: String, apiVersion: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = bugService.getBugList(
                apiKey,
                apiVersion
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    bugList.value = response.body()
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