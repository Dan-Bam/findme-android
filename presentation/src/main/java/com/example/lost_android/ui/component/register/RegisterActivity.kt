package com.example.lost_android.ui.component.register

import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.util.keyboardHide
import com.example.presentation.R
import com.example.presentation.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity: BaseActivity<ActivityRegisterBinding> (R.layout.activity_register) {
    override fun createView() {
        binding.registerContainer.setOnTouchListener { _, _ ->
            keyboardHide(this)
            false
        }
    }
}