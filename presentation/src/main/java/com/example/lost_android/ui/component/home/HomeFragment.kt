package com.example.lost_android.ui.component.home

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lost_android.ui.adapter.LostAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.component.detail.DetailActivity
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding> (R.layout.fragment_home) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var adapter: LostAdapter
    override fun createView() {
        mainViewModel.setTitle(getString(R.string.all))
        homeViewModel.search()
        observeLost()
        initLostList()
        click()
    }

    private fun initLostList() = binding.lostList.apply {
        this@HomeFragment.adapter = LostAdapter {
            requireActivity().startActivity(Intent(context, DetailActivity::class.java).putExtra("lostId", it.id).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        adapter = this@HomeFragment.adapter
        layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeLost() = homeViewModel.lostList.observe(this) {
        adapter.submitList(it)
    }

    private fun click() {
        binding.entryBtn.setOnClickListener { EntryDialog().show(requireActivity().supportFragmentManager, "RegisterDialog") }
    }
}