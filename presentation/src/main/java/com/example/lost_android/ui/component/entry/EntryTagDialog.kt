package com.example.lost_android.ui.component.entry

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.lost_android.util.dp
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.DialogEntryTagBinding

class EntryTagDialog: DialogFragment() {
    private var _binding: DialogEntryTagBinding? = null
    private val binding get() = _binding!!
    private val entryViewModel by activityViewModels<EntryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEntryTagBinding.inflate(inflater, container, false)
        binding.entryTagDialog = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog?.window!!.setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun click(view: View) {
        if (view == binding.addBtn) {
            if (!binding.writeTag.text.isNullOrBlank()) {
                entryViewModel.setTags(binding.writeTag.text.toString())
            }
        }
        dismiss()
    }
}