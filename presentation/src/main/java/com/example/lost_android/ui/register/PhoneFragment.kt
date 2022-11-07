package com.example.lost_android.ui.register

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterPhoneBinding

class PhoneFragment: BaseFragment<FragmentRegisterPhoneBinding> (R.layout.fragment_register_phone) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()

    override fun createView() {
        binding.phone = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.finish()
            R.id.sendBtn -> {}
            R.id.nextBtn -> {
                registerViewModel.setPhone(binding.writePhone.text.toString())
                activity?.findNavController(R.id.registerContainer)
                    ?.navigate(R.id.action_phoneFragment_to_infoFragment)
            }
        }
    }
}