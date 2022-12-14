package com.example.lost_android.ui.component.register

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.util.keyboardHide
import com.example.lost_android.util.setNextBtn
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterPhoneBinding

class PhoneFragment : BaseFragment<FragmentRegisterPhoneBinding>(R.layout.fragment_register_phone) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()

    override fun createView() {
        binding.phone = this
        initEditText()
        observeCertify()
    }

    private fun observeCertify() = registerViewModel.certify.observe(this) {
        if (it) {
            activity?.findNavController(R.id.registerContainer)
                ?.navigate(R.id.action_phoneFragment_to_nameFragment)
        }
    }

    private fun initEditText() {
        binding.writeCertify.setOnTextChanged { p0, _, _, _ ->
            if (p0?.length == 4) {
                setNextBtn(p0, binding.nextBtn)
            } else {
                setNextBtn("", binding.nextBtn)
            }
        }
        binding.writePhone.setOnTextChanged { _, _, _, _ ->
            binding.writeCertify.apply {
                binding.sendBtn.text = getString(R.string.sendNumber)
                visibility = View.GONE
                text = null
            }
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)
                ?.popBackStack()
            R.id.sendBtn -> {
                if (binding.writePhone.text.length == 11) {
                    registerViewModel.setPhone(binding.writePhone.text.toString())
                    keyboardHide(requireActivity())
                    binding.sendBtn.text = getString(R.string.reSend)
                    binding.writeCertify.visibility = View.VISIBLE
                    registerViewModel.sendNum()
                }
            }
            R.id.nextBtn -> {
                registerViewModel.checkNum(binding.writeCertify.text.toString())
            }
        }
    }
}