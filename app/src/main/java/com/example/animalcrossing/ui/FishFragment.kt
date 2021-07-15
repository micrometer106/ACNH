package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.animalcrossing.utils.LogUtils
import com.example.animalcrossing.R
import com.example.animalcrossing.databinding.FishFragmentBinding
import com.example.animalcrossing.viewModel.FishViewModel

class FishFragment: Fragment() {
    private var _binding: FishFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var fishViewModel: FishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FishFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fishViewModel = ViewModelProviders.of(this).get(FishViewModel::class.java)
        fishViewModel.refresh(
            resources.getString(R.string.nookipedia_api_key),
            resources.getString(R.string.nookipedia_api_version)
        )
        observeFishViewModel()
    }

    private fun observeFishViewModel() {
        fishViewModel.fishList.observe(this, Observer {
            LogUtils.d("TTT FishList size = ${it.size}")
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}