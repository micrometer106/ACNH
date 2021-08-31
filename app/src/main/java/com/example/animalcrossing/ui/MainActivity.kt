package com.example.animalcrossing.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.animalcrossing.R
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
            onLoadEvent()
            setOnMenuItemClickListener {
                if (it.itemId == R.id.today_story) {
                    it.icon = AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_today_story_read)
                }
                true
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)
    }

    private fun onLoadEvent() {
        val todayEventVM = TodayEventViewModel.getInstance()
        todayEventVM.refresh(
            getString(R.string.nookipedia_api_key),
            getString(R.string.nookipedia_api_version)
        )
        todayEventVM.event.observe(this, {
            toolbar.menu.getItem(0).apply {
                isVisible = true
            }
        })
    }
}
