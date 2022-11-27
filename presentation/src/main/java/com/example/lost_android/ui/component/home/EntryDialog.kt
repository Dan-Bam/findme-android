package com.example.lost_android.ui.component.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.lost_android.ui.component.entry.EntryActivity
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
            R.id.findEntry -> startActivity(Intent(context, EntryActivity::class.java).putExtra("type", getString(R.string.findEntry)).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            R.id.lostEntry -> startActivity(Intent(context, EntryActivity::class.java).putExtra("type", getString(R.string.lostEntry)).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            R.id.cancel -> dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}