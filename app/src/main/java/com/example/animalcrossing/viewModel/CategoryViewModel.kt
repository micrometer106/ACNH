package com.example.animalcrossing.viewModel

import androidx.lifecycle.ViewModel

abstract class CategoryViewModel: ViewModel() {

    abstract fun refresh(apiKey: String, apiVersion: String)
}