package com.example.lost_android.ui.component.chat

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.ChatRoomAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.ChatViewModel
import com.example.lost_android.viewmodel.MainViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentChatBinding

class ChatFragment: BaseFragment<FragmentChatBinding> (R.layout.fragment_chat) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val chatViewModel by activityViewModels<ChatViewModel>()
    private lateinit var adapter: ChatRoomAdapter
    override fun createView() {
        mainViewModel.setTitle(getString(R.string.chatRoom))
        chatViewModel.chatList()
        initList()
        observeChatList()
    }

    private fun initList() {
        adapter = ChatRoomAdapter() {
            requireActivity().startActivity(Intent(context, ChatActivity::class.java).putExtra("roomId", it.roomId))
        }
        binding.chatList.apply {
            adapter = this@ChatFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeChatList() = chatViewModel.chatList.observe(this) {
        adapter.submitList(it)
    }
}