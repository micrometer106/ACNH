package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossing.utils.LogUtils
import com.example.animalcrossing.R
import com.example.animalcrossing.adapter.FishAdapter
import com.example.animalcrossing.databinding.FishFragmentBinding
import com.example.animalcrossing.viewModel.FishViewModel

class FishFragment: Fragment() {
    private var _binding: FishFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var fishViewModel: FishViewModel
    private var recyclerView : RecyclerView? = null
    private val fishAdapter = FishAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FishFragmentBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerviewFish
        recyclerView?.apply {
            adapter = fishAdapter
            layoutManager = GridLayoutManager(context,3)
        }
        fishViewModel = ViewModelProviders.of(this).get(FishViewModel::class.java)
        binding.loading.isVisible = true
        fishViewModel.refresh(
                resources.getString(R.string.nookipedia_api_key),
                resources.getString(R.string.nookipedia_api_version)
        )
        observeFishViewModel()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun observeFishViewModel() {
        fishViewModel.fishList.observe(this, Observer {
            binding.loading.isVisible = false
            LogUtils.d("TTT FishList size = ${it.size}")
            fishAdapter.setFishList(it)
            fishAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}