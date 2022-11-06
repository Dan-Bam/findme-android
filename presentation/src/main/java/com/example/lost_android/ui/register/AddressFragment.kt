package com.example.lost_android.ui.register

import android.view.View
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterAddressBinding

class AddressFragment: BaseFragment<FragmentRegisterAddressBinding> (R.layout.fragment_register_address) {
    override fun createView() {
        binding.address = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)?.popBackStack()
            R.id.findBtn -> {}
            R.id.nextBtn -> {}
        }
    }
}