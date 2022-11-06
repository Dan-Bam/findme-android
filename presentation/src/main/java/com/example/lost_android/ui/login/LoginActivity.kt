package com.example.lost_android.ui.login

import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.register.RegisterActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity<ActivityLoginBinding> (R.layout.activity_login) {
    override fun createView() {

    }

    fun click(view: View) {
        when (view.id) {
            R.id.registerBtn -> {
                startActivity(Intent(this, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            }
        }
    }
}