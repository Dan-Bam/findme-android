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
    private lateinit var categoryText: List<String>
    private val entryViewModel by activityViewModels<EntryViewModel>()

    override fun createView() {
        binding.entryCategoryFragment = this
        initCategoryList()
        setTitle()
    }

    private fun setTitle() {
        binding.titleTxt.text = entryViewModel.title.value
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> requireActivity().finish()
            R.id.nextBtn -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.entryContainer, EntryFragment()).commit()
            }
            else -> {
                categories.forEachIndexed { index, v ->
                    binding.nextBtn.isEnabled = true
                    binding.nextBtn.isActivated = true
                    if (view == v) {
                        v.setBackgroundResource(R.drawable.bg_category_select)
                        entryViewModel.setCategory(categoryText[index])
                    } else {
                        v.setBackgroundResource(R.drawable.bg_category)
                    }
                }
            }
        }
    }

    private fun initCategoryList() {
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
        categoryText = listOf(
            getString(R.string.phone),
            getString(R.string.laptop),
            getString(R.string.pad),
            getString(R.string.wear),
            getString(R.string.earphone),
            getString(R.string.ring),
            getString(R.string.handRing),
            getString(R.string.neckRing),
            getString(R.string.watch),
            getString(R.string.outer),
            getString(R.string.bag),
            getString(R.string.wallet),
        )
    }
}