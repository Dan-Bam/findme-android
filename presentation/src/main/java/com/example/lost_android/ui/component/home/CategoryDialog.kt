package com.example.lost_android.ui.component.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.example.presentation.R
import com.example.presentation.databinding.DialogCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class CategoryDialog(context: Context, private val action: (String) -> Unit): BottomSheetDialog(context) {
    lateinit var binding: DialogCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryDialog = this
        binding.closeBtn.setOnClickListener { dismiss() }
        window!!.apply {
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.BOTTOM)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.allHolder -> action(context.getString(R.string.all))
            R.id.phoneHolder -> action(context.getString(R.string.phone))
            R.id.laptopHolder -> action(context.getString(R.string.laptop))
            R.id.padHolder -> action(context.getString(R.string.pad))
            R.id.wearHolder -> action(context.getString(R.string.wear))
            R.id.earPhoneHolder -> action(context.getString(R.string.earphone))
            R.id.ringHolder -> action(context.getString(R.string.ring))
            R.id.handRingHolder -> action(context.getString(R.string.handRing))
            R.id.neckRingHolder -> action(context.getString(R.string.neckRing))
            R.id.watchHolder -> action(context.getString(R.string.watch))
            R.id.outerHolder -> action(context.getString(R.string.outer))
            R.id.bagHolder -> action(context.getString(R.string.bag))
            R.id.walletHolder -> action(context.getString(R.string.wallet))
        }
        dismiss()
    }
}