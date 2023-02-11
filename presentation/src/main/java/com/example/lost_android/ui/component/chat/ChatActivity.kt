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
    private lateinit var roomId: String
    private lateinit var adapter: ChatLogAdapter
    override fun createView() {
        binding.chatActivity = this
        roomId = intent.getStringExtra("roomId")!!
        chatViewModel.chatLog(roomId, getSharedPreferences("TOKEN", MODE_PRIVATE).getString("ACCESS_TOKEN", "") ?: "")
        initList()
        observeChat()
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
            R.id.sendBtn -> {
                val message = binding.writeMessage.text.toString()
                if (!message.isNullOrBlank()) {
                    chatViewModel.sendMessage(roomId, message)
                    binding.writeMessage.text = null
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        chatViewModel.disconnect()
    }
}