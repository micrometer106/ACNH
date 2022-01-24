package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossing.R
import com.example.animalcrossing.adapter.BugAdapter
import com.example.animalcrossing.adapter.FishAdapter
import com.example.animalcrossing.adapter.SeaCreatureAdapter
import com.example.animalcrossing.data.NookipediaService
import com.example.animalcrossing.data.database.AppDataBase
import com.example.animalcrossing.data.database.FishDao
import com.example.animalcrossing.data.repository.FishRepository
import com.example.animalcrossing.databinding.SubCreaturesFragmentBinding
import com.example.animalcrossing.model.FishModel
import com.example.animalcrossing.viewModel.*

class SubCreaturesFragment(private val page: Int) : Fragment() {

    private var _binding: SubCreaturesFragmentBinding? = null
    private val binding get() = _binding!!
    private var viewModel: CategoryViewModel? = null
    private var recyclerView : RecyclerView? = null
    private val fishAdapter = FishAdapter()
    private val bugAdapter = BugAdapter()
    private val seaCreatureAdapter = SeaCreatureAdapter()

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
            2 -> seaCreatureAdapter
            else -> null
        }
    }

    private fun getViewModel() : CategoryViewModel? {
        return when (page) {
//            0 -> FishViewModel.getInstance()
            1 -> BugViewModel.getInstance()
            2 -> SeaCreatureViewModel.getInstance()
            else -> null
        }
    }

    private fun observeViewModel() {
        when(page) {
            0 -> observeFishList()
            1 -> observeBugList()
            2 -> observeSeaCreatureList()
        }
    }

    private fun observeFishList() {
        val fishViewModel = ViewModelProviders.of(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return FishViewModel(
                    FishRepository(NookipediaService(), AppDataBase.getInstance(this@SubCreaturesFragment.context!!).fishDao()),
                    resources.getString(R.string.nookipedia_api_key),
                    resources.getString(R.string.nookipedia_api_version)
                ) as T
            }
        })[FishViewModel::class.java]
//        val fishViewModel = viewModel as FishViewModel
        fishViewModel.fishList.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible = false
            fishAdapter.setFishList(it)
            fishAdapter.notifyDataSetChanged()
        })
    }

    private fun observeBugList() {
        val bugViewModel = viewModel as BugViewModel
        bugViewModel.bugList.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible = false
            bugAdapter.setBugList(it)
            bugAdapter.notifyDataSetChanged()
        })
    }

    private fun observeSeaCreatureList() {
        val seaCreatureViewModel = viewModel as SeaCreatureViewModel
        seaCreatureViewModel.seaCreatureList.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible = false
            seaCreatureAdapter.setSeaCreatureList(it)
            seaCreatureAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}