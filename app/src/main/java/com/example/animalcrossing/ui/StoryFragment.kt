package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.animalcrossing.databinding.StoryFragmentBinding
import com.example.animalcrossing.model.Event
import com.example.animalcrossing.model.Villager

class StoryFragment(
    private val event: Event,
    private val villager: Villager?
) : Fragment() {

    private var _binding: StoryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contentText.text = event.event
        villager?.apply {
            Glide
                .with(view.context)
                .load(this.imageUrl)
                .into(binding.villagerImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}