package com.example.lost_android.ui.component.login

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.component.main.MainActivity
import com.example.lost_android.ui.component.register.RegisterActivity
import com.example.lost_android.util.keyboardHide
import com.example.lost_android.viewmodel.LoginViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding> (R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun createView() {
        observeLogin()
        isLogin()
        binding.loginLayout.setOnTouchListener { _, _ ->
            keyboardHide(this)
            false
        }
    }

    private fun isLogin() {
        if (getSharedPreferences("TOKEN", MODE_PRIVATE).getString("ACCESS_TOKEN", null) != null) {
            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            finish()
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.registerBtn -> {
                startActivity(Intent(this, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            }
            R.id.loginBtn -> {
                loginViewModel.login(binding.writeId.text.toString(), binding.writePw.text.toString())
            }
        }
    }

    private fun observeLogin() {
        loginViewModel.state.observe(this) {
            if (it.equals("SUCCESS")) {
                startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                finish()
            }
        }
    }
}