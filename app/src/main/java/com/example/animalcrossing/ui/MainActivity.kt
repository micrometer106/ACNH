package com.example.animalcrossing.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.animalcrossing.R
import com.example.animalcrossing.helper.LoadTodayStoryHelper
import com.example.animalcrossing.model.Event
import com.example.animalcrossing.model.Villager
import com.example.animalcrossing.viewModel.BirthVillagerViewModel
import com.example.animalcrossing.viewModel.TodayEventViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var storyHelper : LoadTodayStoryHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storyHelper = LoadTodayStoryHelper(
            getString(R.string.nookipedia_api_key),
            getString(R.string.nookipedia_api_version)
        )

        toolbar.apply {
            title = getString(R.string.app_name)
            inflateMenu(R.menu.main_menu)
        }

        storyHelper.loadTodayStory(object : LoadTodayStoryHelper.StoryListener{
            override fun onStoryLoaded(
                events: MutableList<Event>,
                villagers: MutableList<Villager>
            ) {
                toolbar.menu.getItem(0).apply {
                    isVisible = true
                    setOnMenuItemClickListener {
                        if (it.itemId == R.id.today_story) {
                            it.icon = AppCompatResources.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_today_story_read
                            )
                            val eventList = arrayListOf<Event>().apply {
                                addAll(events)
                            }
                            val villagerList = arrayListOf<Villager>().apply {
                                addAll(villagers)
                            }
                            val intent = Intent(this@MainActivity, TodayStoryActivity::class.java)
                            intent.apply {
                                action = Intent.ACTION_VIEW
                                putExtra(TodayStoryActivity.TODAY_EVENT, eventList)
                                putExtra(
                                    TodayStoryActivity.TODAY_BIRTH_VILLAGERS,
                                    villagerList
                                )
                            }
                            startActivity(intent)
                        }
                        true
                    }
                }
            }
        })

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        storyHelper.onClear()
    }
}
