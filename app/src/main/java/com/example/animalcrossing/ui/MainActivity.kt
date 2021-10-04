package com.example.animalcrossing.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.animalcrossing.R
import com.example.animalcrossing.data.Event
import com.example.animalcrossing.data.Villager
import com.example.animalcrossing.viewModel.BirthVillagerViewModel
import com.example.animalcrossing.viewModel.TodayEventViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.apply {
            title = getString(R.string.app_name)
            inflateMenu(R.menu.main_menu)
            onLoadEvent { eventList, birthVillagerList ->
                toolbar.menu.getItem(0).apply {
                    isVisible = true
                    setOnMenuItemClickListener {
                        if (it.itemId == R.id.today_story) {
                            it.icon = AppCompatResources.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_today_story_read
                            )
                            val intent = Intent(this@MainActivity, TodayStoryActivity::class.java)
                            intent.apply {
                                action = Intent.ACTION_VIEW
                                putExtra(TodayStoryActivity.TODAY_EVENT, eventList)
                                putExtra(
                                    TodayStoryActivity.TODAY_BIRTH_VILLAGERS,
                                    birthVillagerList
                                )
                            }
                            startActivity(intent)
                        }
                        true
                    }
                }
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)
    }

    private fun onLoadEvent(
        callback: (events: ArrayList<Event>, birthVillager: ArrayList<Villager>) -> Unit
    ) {
        val todayEventVM = TodayEventViewModel.getInstance()
        todayEventVM.refresh(
            getString(R.string.nookipedia_api_key),
            getString(R.string.nookipedia_api_version)
        )
        todayEventVM.event.observe(this, {
            val events = arrayListOf<Event>()
            events.addAll(it)

            if (events.size > 0) {
                onLoadBirthVillagers(it) {
                    val birthVillager = arrayListOf<Villager>()
                    birthVillager.addAll(it)
                    callback(events, birthVillager)
                }
            }

        })
    }

    private fun onLoadBirthVillagers(
        events: MutableList<Event>,
        callback: (birthVillager: MutableList<Villager>) -> Unit
    ) {
        var isBirthdayExist = false
        for (event in events) {
            if (event.type == "Birthday") {
                isBirthdayExist = true
                break
            }
        }

        if (isBirthdayExist) {
            val birthVillagerVM = BirthVillagerViewModel.getInstance()
            birthVillagerVM.refresh(
                getString(R.string.nookipedia_api_key),
                getString(R.string.nookipedia_api_version)
            )
            birthVillagerVM.villager.observe(this, {
                callback(it)
            })
        } else {
            callback(mutableListOf())
        }
    }
}
