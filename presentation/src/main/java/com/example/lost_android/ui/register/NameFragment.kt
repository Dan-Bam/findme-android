package com.example.lost_android.ui.register

import android.view.View
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterNameBinding

class NameFragment: BaseFragment<FragmentRegisterNameBinding> (R.layout.fragment_register_name) {
    override fun createView() {
        binding.name = this
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)?.popBackStack()
            R.id.nextBtn -> activity?.findNavController(R.id.registerContainer)?.navigate(R.id.action_nameFragment_to_addressFragment)
        }
    }
}