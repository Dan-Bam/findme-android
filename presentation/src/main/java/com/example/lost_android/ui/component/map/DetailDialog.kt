package com.example.lost_android.ui.component.map

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.example.lost_android.ui.adapter.TagAdapter
import com.example.lost_android.ui.component.map.model.MapData
import com.example.presentation.R
import com.example.presentation.databinding.DialogDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class DetailDialog(context: Context, val data: MapData): BottomSheetDialog(context) {
    private lateinit var binding: DialogDetailBinding
    private lateinit var adapter: TagAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.detailDialog = this
        binding.mapData = data
        binding.lostImg.load(data.img)
        initTagList()
        adapter.submitList(data.tags)
        window!!.apply {
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.BOTTOM)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.closeBtn -> dismiss()
        }
    }

    private fun initTagList() = binding.tagList.apply {
        this@DetailDialog.adapter = TagAdapter()
        adapter = this@DetailDialog.adapter
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}