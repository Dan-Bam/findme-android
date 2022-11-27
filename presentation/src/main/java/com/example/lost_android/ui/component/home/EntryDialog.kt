package com.example.lost_android.ui.component.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.presentation.R
import com.example.presentation.databinding.DialogEntryBinding

class EntryDialog: DialogFragment() {
    private var _binding: DialogEntryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEntryBinding.inflate(inflater, container, false)
        binding.entryDialog = this
        return binding.root
    }

    fun click(view: View) {
        when (view.id) {
            R.id.findEntry -> {}
            R.id.lostEntry -> {}
            R.id.cancel -> dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}