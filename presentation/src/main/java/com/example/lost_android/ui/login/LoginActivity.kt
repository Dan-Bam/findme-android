package com.example.lost_android.ui.login

import android.content.Intent
import android.view.View
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.main.MainActivity
import com.example.lost_android.ui.register.RegisterActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding> (R.layout.activity_login) {
    override fun createView() {

    }

    fun click(view: View) {
        when (view.id) {
            R.id.registerBtn -> {
                startActivity(Intent(this, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            }
            R.id.loginBtn -> {
                startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                finish()
            }
        }
    }
}