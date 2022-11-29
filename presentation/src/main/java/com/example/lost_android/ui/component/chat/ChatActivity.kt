package com.example.lost_android.ui.component.chat

import android.view.View
import com.example.lost_android.ui.base.BaseActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity: BaseActivity<ActivityChatBinding> (R.layout.activity_chat) {
    override fun createView() {
        binding.chatActivity = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> {
                finish()
            }
        }
    }
}