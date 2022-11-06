package com.example.lost_android.ui.register

import android.view.View
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterPhoneBinding

class PhoneFragment: BaseFragment<FragmentRegisterPhoneBinding> (R.layout.fragment_register_phone) {
    override fun createView() {
        binding.phone = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.finish()
            R.id.sendBtn -> {}
            R.id.nextBtn -> activity?.findNavController(R.id.registerContainer)?.navigate(R.id.action_phoneFragment_to_infoFragment)
        }
    }
}