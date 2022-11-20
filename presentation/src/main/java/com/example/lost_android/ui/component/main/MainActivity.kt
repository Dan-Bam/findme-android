package com.example.lost_android.ui.component.main

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lost_android.ui.base.BaseActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding> (R.layout.activity_main) {
    override fun createView() {
        binding.bottomNav.itemIconTintList = null
        settingBottomNav()
    }

    private fun settingBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.mainContainer)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}