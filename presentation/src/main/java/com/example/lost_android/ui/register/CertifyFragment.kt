package com.example.lost_android.ui.register

import android.telephony.PhoneNumberUtils
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.util.convertNumberToPhoneNumber
import com.example.lost_android.util.setNextBtn
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterCertifyBinding
import java.util.Locale

class CertifyFragment :
    BaseFragment<FragmentRegisterCertifyBinding>(R.layout.fragment_register_certify) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()
    override fun createView() {
        binding.certify = this
        binding.requestNumber.text = "${registerViewModel.registerData.value?.phone?.convertNumberToPhoneNumber()}으로\n인증번호를 보냈어요."
        initEditText()
    }

    private fun initEditText() {
        binding.writeNumber.setOnTextChanged { p0, _, _, _ ->
            if (p0?.length == 4) {
                setNextBtn(p0, binding.nextBtn)
            } else {
                setNextBtn("", binding.nextBtn)
            }
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)
                ?.popBackStack()
            R.id.sendBtn -> {}
            R.id.nextBtn -> {
                activity?.findNavController(R.id.registerContainer)
                    ?.navigate(R.id.action_certifyFragment_to_nameFragment)
            }
        }
    }
}