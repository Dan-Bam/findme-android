package com.example.lost_android.ui.component.chat

import androidx.fragment.app.activityViewModels
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentChatBinding

class ChatFragment: BaseFragment<FragmentChatBinding> (R.layout.fragment_chat) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun createView() {
        mainViewModel.setTitle(getString(R.string.chatRoom))
    }
}