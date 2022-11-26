package com.example.lost_android.ui.component.main

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding> (R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    override fun createView() {
        binding.bottomNav.itemIconTintList = null
        settingBottomNav()
        observeTitle()
    }

    private fun observeTitle() = mainViewModel.currentTitle.observe(this) {
        binding.titleTxt.text = it
        val optionView = arrayOf(binding.optionBtn, binding.locateTxt, binding.optionLocationBtn)
        if (it.equals(getString(R.string.findItem)) || it.equals(getString(R.string.chatRoom))) {
            optionView.forEach { it.visibility = View.GONE }
        } else {
            optionView.forEach { it.visibility = View.VISIBLE }
        }
    }

    private fun settingBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.mainContainer)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}