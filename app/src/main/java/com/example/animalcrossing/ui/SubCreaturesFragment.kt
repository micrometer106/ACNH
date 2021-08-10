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
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossing.R
import com.example.animalcrossing.adapter.BugAdapter
import com.example.animalcrossing.adapter.FishAdapter
import com.example.animalcrossing.databinding.SubCreaturesFragmentBinding
import com.example.animalcrossing.viewModel.BugViewModel
import com.example.animalcrossing.viewModel.CreaturesViewModel
import com.example.animalcrossing.viewModel.FishViewModel

class SubCreaturesFragment(private val page: Int) : Fragment() {

    private var _binding: SubCreaturesFragmentBinding? = null
    private val binding get() = _binding!!
    private var viewModel: CreaturesViewModel? = null
    private var recyclerView : RecyclerView? = null
    private val fishAdapter = FishAdapter()
    private val bugAdapter = BugAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubCreaturesFragmentBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerviewCreatures
        recyclerView?.apply {
            adapter = getCreaturesAdapter()
            layoutManager = GridLayoutManager(context,3)
        }
        viewModel = getViewModel()
        binding.loading.isVisible = true
        viewModel?.refresh(
            resources.getString(R.string.nookipedia_api_key),
            resources.getString(R.string.nookipedia_api_version)
        )
        observeViewModel()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getCreaturesAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>? {
        return when(page) {
            0 -> fishAdapter
            1 -> bugAdapter
            else -> null
        }
    }

    private fun getViewModel() : CreaturesViewModel? {
        return when (page) {
            0 -> ViewModelProviders.of(this).get(FishViewModel::class.java)
            1 -> ViewModelProviders.of(this).get(BugViewModel::class.java)
            else -> null
        }
    }

    private fun observeViewModel() {
        when(page) {
            0 -> observeFishList()
            1 -> observeBugList()
        }
    }

    private fun observeFishList() {
        val fishViewModel = viewModel as FishViewModel
        fishViewModel.fishList.observe(this, Observer {
            binding.loading.isVisible = false
            fishAdapter.setFishList(it)
            fishAdapter.notifyDataSetChanged()
        })
    }

    private fun observeBugList() {
        val bugViewModel = viewModel as BugViewModel
        bugViewModel.bugList.observe(this, Observer {
            binding.loading.isVisible = false
            bugAdapter.setBugList(it)
            bugAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}