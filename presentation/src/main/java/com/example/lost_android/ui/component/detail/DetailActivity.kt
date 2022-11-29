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
    private lateinit var type: String
    private lateinit var id: String
    override fun createView() {
        binding.detailActivity = this
        type = intent.getStringExtra("type")!!
        id = intent.getStringExtra("lostId")!!
        if (type == getString(R.string.editLost)) {
            detailViewModel.detailLost(id)
        } else {
            detailViewModel.detailFound(id)
        }
        observeEntryData()
        initTagList()
    }

    private fun observeEntryData() {
        detailViewModel.lostData.observe(this) {
            if (it.isMine) {
                binding.settingLayout.visibility = View.VISIBLE
            } else {
                binding.findBtn.visibility = View.VISIBLE
            }
            binding.lostImg.load(it.lostImages[0])
            binding.lostEntity = it
            adapter.submitList(it.tags)
        }
        detailViewModel.foundData.observe(this) {
            binding.settingLayout.visibility = View.VISIBLE
            binding.lostImg.load(it.lostImages[0])
            binding.titleTxt.text = it.title
            binding.descriptionTxt.text = it.description
            adapter.submitList(it.tags)
        }
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
                if (type == getString(R.string.editLost)) {
                    detailViewModel.deleteLost()
                } else {
                    detailViewModel.deleteFound()
                }
                finish()
            }
            R.id.editBtn -> {
                startActivity(Intent(this, EntryActivity::class.java).putExtra("type", type).putExtra("id", id))
            }
        }
    }
}