package com.example.lost_android.ui.register

import android.view.View
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterInfoBinding

class InfoFragment: BaseFragment<FragmentRegisterInfoBinding> (R.layout.fragment_register_info) {
    override fun createView() {
        binding.info = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)?.popBackStack()
            R.id.nextBtn -> activity?.findNavController(R.id.registerContainer)?.navigate(R.id.action_infoFragment_to_nameFragment)
        }
    }
}