package com.example.lost_android.ui.splash

import android.content.Intent
import android.os.Handler
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.login.LoginActivity
import com.example.lost_android.ui.main.MainActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivitySplashBinding

class SplashActivity: BaseActivity<ActivitySplashBinding> (R.layout.activity_splash) {
    override fun createView() {
        Handler().postDelayed({
            if (true) {
                startActivity(Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            } else {
                startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            }
            finish()
        }, 1000)
    }
}