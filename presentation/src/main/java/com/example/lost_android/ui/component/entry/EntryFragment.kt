package com.example.lost_android.ui.component.entry

import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEntryBinding

class EntryFragment : BaseFragment<FragmentEntryBinding>(R.layout.fragment_entry) {
    private val entryViewModel by activityViewModels<EntryViewModel>()
    override fun createView() {
        binding.entryFragment = this
        setTitle()
        isLost()
        isSelectLocate()
    }

    private fun setTitle() {
        binding.titleTxt.text = entryViewModel.title.value
    }

    private fun isLost() {
        if (entryViewModel.title.value != getString(R.string.lostEntry)) with(binding) {
            imageEntry.text = getString(R.string.findImageEntry)
            imageEntryTxt.text = getString(R.string.findImageEntry)
            securityService.visibility = View.INVISIBLE
            whatSecurityService.visibility = View.INVISIBLE
            isSafeSwitch.visibility = View.INVISIBLE
        }
    }

    private fun isSelectLocate() {
        if (entryViewModel.currentAddress.value != null) {
            binding.selectLocateTxt.text = entryViewModel.currentAddress.value!!.address
            binding.selectedLocateLayout.visibility = View.VISIBLE
            binding.unSelectLocationLayout.visibility = View.GONE
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.entryContainer, EntryCategoryFragment()).commit()
            R.id.chooseLocationBtn -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.entryContainer, EntryMapFragment()).commit()
        }
    }
}