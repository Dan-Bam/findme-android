package com.example.lost_android.ui.start

import android.content.Intent
import android.view.View
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.login.LoginActivity
import com.example.lost_android.ui.register.RegisterActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityStartBinding

class StartActivity: BaseActivity<ActivityStartBinding> (R.layout.activity_start) {
    override fun createView() {

    }

    fun click(view: View) {
        when (view.id) {
            R.id.loginBtn -> startActivity(Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            R.id.registerBtn -> startActivity(Intent(this, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        finish()
    }
}