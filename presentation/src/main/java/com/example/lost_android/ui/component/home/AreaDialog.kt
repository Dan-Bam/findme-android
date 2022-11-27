package com.example.lost_android.ui.component.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.AreaAdapter
import com.example.lost_android.util.dp
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.presentation.databinding.DialogAreaBinding

class AreaDialog: DialogFragment() {
    private var _binding: DialogAreaBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var adapter: AreaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAreaBinding.inflate(inflater, container, false)
        binding.searchAreaTxt.setOnTextChanged { p0, _, _, _ ->
            homeViewModel.searchArea(p0.toString())
        }
        adapter = AreaAdapter {
            homeViewModel.setArea(it)
            dismiss()
        }
        initList()
        homeViewModel.areaList.observe(this) {
            adapter.submitList(it)
        }
        return binding.root
    }

    private fun initList() = binding.areaList.apply {
        this.adapter = this@AreaDialog.adapter
        this.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog?.window!!.setLayout(width, 400.dp(requireContext()))
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}