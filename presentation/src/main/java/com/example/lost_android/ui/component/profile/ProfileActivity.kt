package com.example.lost_android.ui.component.profile

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.EntryAdapter
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.component.detail.DetailActivity
import com.example.lost_android.viewmodel.ProfileViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private lateinit var adapter: EntryAdapter
    private val profileViewModel by viewModels<ProfileViewModel>()
    private var type: String? = null
    override fun createView() {
        binding.profileActivity = this
        profileViewModel.getInfo()
        binding.myLost.isSelected = true
        observeInfo()
        observeEntry()
        initAdapter()
    }

    private fun initAdapter() {
        adapter = EntryAdapter(this) {
            startActivity(Intent(this, DetailActivity::class.java).putExtra("lostId", it.id).putExtra("type", type ?: getString(R.string.editLost)).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.entryList.apply {
            adapter = this@ProfileActivity.adapter
            layoutManager = GridLayoutManager(this@ProfileActivity, 2)
        }
    }

    private fun observeInfo() = profileViewModel.info.observe(this) {
        binding.nameTxt.text = "${it.userName}님"
    }

    private fun observeEntry() = profileViewModel.myEntry.observe(this) {
        adapter.submitList(it)
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backTxt, R.id.backBtn -> finish()
            R.id.recommendLost -> {}
            R.id.myLost -> {
                type = getString(R.string.editLost)
                profileViewModel.myLost()
            }
            R.id.myFound -> {
                type = getString(R.string.editFound)
                profileViewModel.myFound()
            }
        }
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