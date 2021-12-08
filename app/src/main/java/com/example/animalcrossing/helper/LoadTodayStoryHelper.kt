package com.example.animalcrossing.helper

import androidx.lifecycle.Observer
import com.example.animalcrossing.model.Event
import com.example.animalcrossing.model.Villager
import com.example.animalcrossing.viewModel.BirthVillagerViewModel
import com.example.animalcrossing.viewModel.TodayEventViewModel

class LoadTodayStoryHelper(
    private val apiKey: String,
    private val apiVersion: String
) {

    interface StoryListener {
        fun onStoryLoaded(events: MutableList<Event>, villagers: MutableList<Villager>)
    }
    private lateinit var storyListener: StoryListener
    private val todayEventVM = TodayEventViewModel.getInstance()
    private val birthVillagerVM = BirthVillagerViewModel.getInstance()
    private val eventObserver = Observer<MutableList<Event>> {
        val events = arrayListOf<Event>()
        events.addAll(it)

        if (events.size > 0) {
            getBirthVillager(it)
        }
    }

    private val birthObserver = Observer<MutableList<Villager>> {
        storyListener.onStoryLoaded(todayEventVM.event.value!!, it)
    }

    fun loadTodayStory(storyListener: StoryListener) {
        todayEventVM.refresh(apiKey, apiVersion)
        todayEventVM.event.observeForever(eventObserver)
        this.storyListener = storyListener
    }

    private fun getBirthVillager(events: MutableList<Event>) {
        events.takeWhile { it.type == "Birthday" }.apply {
            if (this.isNotEmpty()) {
                birthVillagerVM.refresh(apiKey, apiVersion)
                birthVillagerVM.villager.observeForever(birthObserver)
            } else {
                storyListener.onStoryLoaded(this.toMutableList(), mutableListOf())
            }
        }
    }

    fun onClear() {
        todayEventVM.event.removeObserver(eventObserver)
        birthVillagerVM.villager.removeObserver(birthObserver)
    }
}