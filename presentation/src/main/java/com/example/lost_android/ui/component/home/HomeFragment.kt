package com.example.lost_android.ui.component.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding> (R.layout.fragment_home) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun createView() {
        mainViewModel.setTitle("전체보기")
    }
}