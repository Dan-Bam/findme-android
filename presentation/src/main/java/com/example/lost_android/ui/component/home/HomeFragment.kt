package com.example.lost_android.ui.component.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.LostAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding> (R.layout.fragment_home) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var adapter: LostAdapter
    override fun createView() {
        mainViewModel.setTitle("전체보기")
        homeViewModel.search()
        adapter = LostAdapter {

        }
        observeLost()
        binding.lostList.adapter = adapter
        binding.lostList.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeLost() = homeViewModel.lostList.observe(this) {
        adapter.submitList(it)
    }
}