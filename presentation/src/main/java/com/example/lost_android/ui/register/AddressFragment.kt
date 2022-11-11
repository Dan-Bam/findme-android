package com.example.lost_android.ui.register

import android.content.Intent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.login.LoginActivity
import com.example.lost_android.util.setNextBtn
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterAddressBinding

class AddressFragment: BaseFragment<FragmentRegisterAddressBinding> (R.layout.fragment_register_address) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()
    override fun createView() {
        binding.address = this
        initEditText()
    }

    private fun initEditText() {
        binding.writeAddress.setOnTextChanged { p0, _, _, _ ->
            setNextBtn(p0, binding.finishBtn)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)?.popBackStack()
            R.id.findBtn -> {}
            R.id.finishBtn -> {
                registerViewModel.setAddress(binding.writeAddress.text.toString())
                registerViewModel.signUp()
                startActivity(Intent(context, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                activity?.finish()
            }
        }
    }
}