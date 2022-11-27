package com.example.lost_android.ui.component.main

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.component.home.AreaDialog
import com.example.lost_android.ui.component.home.CategoryDialog
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding> (R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun createView() {
        binding.mainActivity = this
        binding.bottomNav.itemIconTintList = null
        settingBottomNav()
        observeTitle()
        observeArea()
        observeCategory()
    }

    fun click(view: View) {
        when (view.id) {
            R.id.locateTxt, R.id.optionLocationBtn -> {
                AreaDialog().show(supportFragmentManager, "AreaDialog")
            }
            R.id.titleTxt, R.id.optionBtn -> {
                CategoryDialog(this) {
                    homeViewModel.setCategory(it)
                }.show()
            }
        }
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

    private fun observeArea() = homeViewModel.currentArea.observe(this) {
        binding.locateTxt.text = it.areaName
        homeViewModel.search()
    }

    private fun observeCategory() = homeViewModel.currentCategory.observe(this) {
        if (it == null) {
            binding.titleTxt.text = getString(R.string.all)
        } else {
            binding.titleTxt.text = it
        }
        homeViewModel.search()
    }

    private fun settingBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.mainContainer)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}