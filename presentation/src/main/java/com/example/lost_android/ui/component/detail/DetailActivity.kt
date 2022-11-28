package com.example.lost_android.ui.component.detail

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.example.lost_android.ui.adapter.TagAdapter
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.ui.component.entry.EntryActivity
import com.example.lost_android.viewmodel.DetailViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var adapter: TagAdapter
    override fun createView() {
        binding.detailActivity = this
        if (intent.getStringExtra("type") == "lost") {
            detailViewModel.getDetail(intent.getStringExtra("lostId")!!)
        }
        observeLostData()
        initTagList()
    }

    private fun observeLostData() = detailViewModel.lostData.observe(this) {
        if (it.isMine) {
            binding.settingLayout.visibility = View.VISIBLE
        }
        binding.lostImg.load(it.lostImages[0])
        binding.lostEntity = it
        adapter.submitList(it.tags)
    }

    private fun initTagList() {
        adapter = TagAdapter()
        binding.tagList.apply {
            adapter = this@DetailActivity.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> finish()
            R.id.settingBtn -> {
                binding.settingBtn.isActivated = !binding.settingBtn.isActivated
                arrayOf(binding.deleteBtn, binding.editBtn).forEach {
                    it.visibility = if (binding.settingBtn.isActivated) View.VISIBLE else View.GONE
                }
            }
            R.id.deleteBtn -> {
                detailViewModel.deleteLost()
                finish()
            }
            R.id.editBtn -> {
                startActivity(Intent(this, EntryActivity::class.java).putExtra("type", "edit"))
            }
        }
    }
}