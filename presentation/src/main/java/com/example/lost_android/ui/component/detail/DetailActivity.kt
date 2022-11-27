package com.example.lost_android.ui.component.detail

import android.view.View
import com.example.lost_android.ui.base.BaseActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun createView() {
        binding.detailActivity = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> finish()
        }
    }
}