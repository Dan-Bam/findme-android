package com.example.lost_android.ui.component.profile

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lost_android.ui.adapter.MyFoundAdapter
import com.example.lost_android.ui.adapter.MyLostAdapter
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.component.detail.DetailActivity
import com.example.lost_android.viewmodel.ProfileViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private lateinit var myFoundAdapter: MyFoundAdapter
    private lateinit var myLostAdapter: MyLostAdapter
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
        myFoundAdapter = MyFoundAdapter(this) {
            startActivity(
                Intent(this, DetailActivity::class.java).putExtra("lostId", it.id)
                    .putExtra("type", type ?: getString(R.string.editLost))
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        myLostAdapter = MyLostAdapter(this) {
            startActivity(
                Intent(this, DetailActivity::class.java).putExtra("lostId", it.id)
                    .putExtra("type", type ?: getString(R.string.editLost))
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        binding.foundList.apply {
            adapter = this@ProfileActivity.myFoundAdapter
            layoutManager = GridLayoutManager(this@ProfileActivity, 2)
        }
        binding.lostList.apply {
            adapter = this@ProfileActivity.myLostAdapter
            layoutManager = GridLayoutManager(this@ProfileActivity, 2)
        }
    }

    private fun observeInfo() = profileViewModel.info.observe(this) {
        binding.nameTxt.text = "${it.userName}님"
    }

    private fun observeEntry() {
        profileViewModel.myLost.observe(this) {
            myLostAdapter.submitList(it)
        }
        profileViewModel.myFound.observe(this) {
            println("안녕 $it")
            myFoundAdapter.submitList(it)
        }
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
            when (view) {
                binding.recommendLost -> {
                    binding.recommendList.visibility = View.VISIBLE
                    binding.foundList.visibility = View.GONE
                    binding.lostList.visibility = View.GONE
                }
                binding.myFound -> {
                    binding.recommendList.visibility = View.GONE
                    binding.foundList.visibility = View.VISIBLE
                    binding.lostList.visibility = View.GONE
                }
                else -> {
                    binding.recommendList.visibility = View.GONE
                    binding.foundList.visibility = View.GONE
                    binding.lostList.visibility = View.VISIBLE
                }
            }
        }
    }
}