package com.example.animalcrossing.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.animalcrossing.databinding.TodayStoryActivityBinding
import com.example.animalcrossing.model.Event
import com.example.animalcrossing.model.Villager

class TodayStoryActivity : AppCompatActivity() {
    companion object{
        const val TODAY_EVENT = "TODAY_EVENT"
        const val TODAY_BIRTH_VILLAGERS = "TODAY_BIRTH_VILLAGERS"
    }

    private lateinit var binding: TodayStoryActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TodayStoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val events = intent.getSerializableExtra(TODAY_EVENT)
        val birthVillager = intent.getSerializableExtra(TODAY_BIRTH_VILLAGERS)

        val pager = binding.pager
        pager.adapter = StoryViewPager(
            this,
            events as ArrayList<Event>,
            birthVillager as ArrayList<Villager>
        )
    }

    class StoryViewPager(
        fragmentActivity: FragmentActivity,
        private val events: ArrayList<Event>,
        private val birthVillager: ArrayList<Villager>
        ): FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = events.size

        override fun createFragment(position: Int): Fragment {
            val event = events[position]
            var villager: Villager? = null
            if (event.type == "Birthday") {
                val removeIndex = event.event.indexOf("'")
                val birthName = event.event.substring(0, removeIndex)
                for (i in 0 until birthVillager.size) {
                    if (birthVillager[i].name == birthName) {
                        villager = birthVillager[i]
                    }
                }
            }
            return StoryFragment(event, villager)
        }
    }
}