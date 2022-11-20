package com.example.lost_android.ui.component.register

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.util.Keyboard
import com.example.lost_android.util.setNextBtn
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterInfoBinding

class InfoFragment: BaseFragment<FragmentRegisterInfoBinding> (R.layout.fragment_register_info) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()
    override fun createView() {
        binding.info = this
        initEditText()
        registerViewModel.getAddress("남풍빌라")
    }

    private fun initEditText() {
        binding.writeId.setOnTextChanged { p0, _, _, _ ->
            setNextBtn(p0, binding.nextBtn, pw = binding.writePw, pwRe = binding.writeRePw, type = Keyboard.ID)
        }
        binding.writePw.setOnTextChanged { p0, _, _, _ ->
            setNextBtn(p0, binding.nextBtn, id = binding.writeId, pw = binding.writeRePw, type = Keyboard.PW)
        }
        binding.writeRePw.setOnTextChanged { p0, _, _, _ ->
            setNextBtn(p0, binding.nextBtn, id = binding.writeId, pw = binding.writePw, type = Keyboard.PW)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.finish()
            R.id.nextBtn -> {
                registerViewModel.setInfo(binding.writeId.text.toString(), binding.writePw.text.toString())
                activity?.findNavController(R.id.registerContainer)
                    ?.navigate(R.id.action_infoFragment_to_phoneFragment)
            }
        }
    }
}