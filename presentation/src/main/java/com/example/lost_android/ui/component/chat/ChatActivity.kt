package com.example.lost_android.ui.component.chat

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.ChatLogAdapter
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.viewmodel.ChatViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity: BaseActivity<ActivityChatBinding> (R.layout.activity_chat) {
    private val chatViewModel by viewModels<ChatViewModel>()
    private lateinit var adapter: ChatLogAdapter
    override fun createView() {
        binding.chatActivity = this
        initList()
        observeChat()
        println("안녕 ${intent.getStringExtra("roomId")!!}")
        chatViewModel.chatLog(intent.getStringExtra("roomId")!!)
    }

    private fun initList() {
        adapter = ChatLogAdapter()
        binding.chatLogList.apply {
            adapter = this@ChatActivity.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeChat() = chatViewModel.chatLog.observe(this) {
        adapter.submitList(it)
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> {
                finish()
            }
        }
    }
}