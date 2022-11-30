package com.example.lost_android.ui.component.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.lost_android.viewmodel.DetailViewModel
import com.example.presentation.R
import com.example.presentation.databinding.DialogEntryDeleteBinding

class DeleteDialog(private val type: String): DialogFragment() {
    private var _binding: DialogEntryDeleteBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by activityViewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEntryDeleteBinding.inflate(inflater, container, false)
        binding.deleteEntryDialog = this
        detailViewModel.isDelete.observe(this) {
            if (it) {
                requireActivity().finish()
                dismiss()
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog?.window!!.setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun click(view: View) {
        if (view == binding.deleteBtn) {
            if (type == getString(R.string.editLost) || type == getString(R.string.lostEntry)) {
                detailViewModel.deleteLost()
            } else {
                detailViewModel.deleteFound()
            }
        } else {
            dismiss()
        }
    }
}