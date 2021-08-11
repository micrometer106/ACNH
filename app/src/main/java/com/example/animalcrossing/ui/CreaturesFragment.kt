package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.animalcrossing.databinding.CreaturesFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class CreaturesFragment : Fragment() {

    private var _binding: CreaturesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var creaturesPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreaturesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        creaturesPager = binding.pager
        creaturesPager.adapter = CreateuresPagerAdapter(activity as FragmentActivity)
        TabLayoutMediator(binding.tabLayout, creaturesPager) { tab, position ->

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class CreateuresPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return SubCreaturesFragment(position)
        }
    }
}