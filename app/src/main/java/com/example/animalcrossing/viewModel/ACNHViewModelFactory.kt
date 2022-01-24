package com.example.animalcrossing.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ACNHViewModelFactory(
    private val apiKey: String,
    private val apiVersion: String
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ACNHViewModelFactory(apiKey, apiVersion) as T
}