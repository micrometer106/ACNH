package com.example.animalcrossing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.animalcrossing.databinding.MuseumFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class MuseumFragment: Fragment() {

    private var _binding: MuseumFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var museumPager: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = MuseumFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        museumPager = binding.pager
        museumPager.adapter = MuseumPagerAdapter(activity as FragmentActivity)
        TabLayoutMediator(binding.tabLayout, museumPager) { _, _ ->

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class MuseumPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return SubMuseumFragment(position)
        }

    }
}