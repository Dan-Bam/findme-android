package com.example.lost_android.ui.component.entry

import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEntryCategoryBinding

class EntryCategoryFragment :
    BaseFragment<FragmentEntryCategoryBinding>(R.layout.fragment_entry_category) {
    private lateinit var categories: List<View>
    private val entryViewModel by activityViewModels<EntryViewModel>()

    override fun createView() {
        binding.entryCategoryFragment = this
        categories = listOf(
            binding.phoneHolder,
            binding.laptopHolder,
            binding.padHolder,
            binding.wearHolder,
            binding.earPhoneHolder,
            binding.ringHolder,
            binding.handRingHolder,
            binding.neckRingHolder,
            binding.watchHolder,
            binding.outerHolder,
            binding.bagHolder,
            binding.walletHolder
        )
        entryViewModel.setTitle(requireActivity().intent.getStringExtra("type")!!)
        observeTitle()
    }

    private fun observeTitle() = entryViewModel.title.observe(this) {
        binding.titleTxt.text = it
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> requireActivity().finish()
            R.id.nextBtn -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.entryContainer, EntryFragment()).commit()
            }
            else -> {
                categories.forEach {
                    binding.nextBtn.isEnabled = true
                    binding.nextBtn.isActivated = true
                    if (view == it) {
                        it.setBackgroundResource(R.drawable.bg_category_select)
                    } else {
                        it.setBackgroundResource(R.drawable.bg_category)
                    }
                }
            }
        }
    }
}