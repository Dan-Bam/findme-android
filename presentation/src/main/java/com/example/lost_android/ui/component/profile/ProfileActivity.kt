package com.example.lost_android.ui.component.profile

import android.view.View
import androidx.activity.viewModels
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.viewmodel.ProfileViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val profileViewModel by viewModels<ProfileViewModel>()
    override fun createView() {
        binding.profileActivity = this
        profileViewModel.getInfo()
        observeInfo()
        binding.myLost.isSelected = true
    }

    private fun observeInfo() = profileViewModel.info.observe(this) {
        binding.nameTxt.text = "${it.userName}님"
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backTxt, R.id.backBtn -> finish()
            else -> {
                arrayOf(binding.recommendLost, binding.myLost, binding.myFound).forEach {
                    it.isSelected = it == view
                    if (view == binding.recommendLost) {
                        binding.recommendList.visibility = View.VISIBLE
                        binding.entryList.visibility = View.GONE
                    } else {
                        binding.recommendList.visibility = View.GONE
                        binding.entryList.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}