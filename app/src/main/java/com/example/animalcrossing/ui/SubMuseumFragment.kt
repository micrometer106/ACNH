package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcrossing.R
import com.example.animalcrossing.adapter.ArtAdapter
import com.example.animalcrossing.databinding.SubMuseumFragmentBinding
import com.example.animalcrossing.viewModel.ArtViewModel
import com.example.animalcrossing.viewModel.CategoryViewModel

class SubMuseumFragment(private val page: Int) : Fragment() {
    private var _binding: SubMuseumFragmentBinding? = null
    private val binding get() = _binding!!
    private var viewModel: CategoryViewModel? = null
    private var recyclerView: RecyclerView? = null
    private val artAdapter = ArtAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubMuseumFragmentBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerviewMuseum
        recyclerView?.apply {
            adapter = getMuseumAdapter()
            layoutManager = GridLayoutManager(context, 3)
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

    private fun getMuseumAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>? {
        return when(page) {
            0 -> artAdapter
            else -> null
        }
    }

    private fun getViewModel() : CategoryViewModel? {
        return when(page) {
            0 -> ArtViewModel.getInstance()
            else -> null
        }
    }

    private fun observeViewModel() {
        when(page) {
            0 -> observeArtLit()
        }
    }

    private fun observeArtLit() {
        val artViewModel = viewModel as ArtViewModel
        artViewModel.artList.observe(viewLifecycleOwner, {
            binding.loading.isVisible = false
            artAdapter.setArtList(it)
            artAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}