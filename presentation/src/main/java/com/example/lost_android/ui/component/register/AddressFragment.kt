package com.example.lost_android.ui.component.register

import android.content.Intent
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lost_android.ui.adapter.AddressAdapter
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.ui.component.login.LoginActivity
import com.example.lost_android.util.keyboardHide
import com.example.lost_android.util.keyboardShow
import com.example.lost_android.util.setNextBtn
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRegisterAddressBinding
import okhttp3.OkHttpClient
import okhttp3.Request

class AddressFragment :
    BaseFragment<FragmentRegisterAddressBinding>(R.layout.fragment_register_address) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()
    override fun createView() {
        binding.address = this
        initEditText()
        observeAddress()
    }

    private fun observeAddress() = registerViewModel.addressInfo.observe(this) {
        binding.addressList.apply {
            visibility = View.VISIBLE
            adapter = AddressAdapter(it) {
                visibility = View.GONE
                binding.writeAddressTxt.visibility = View.VISIBLE
                binding.writeDetailAddress.visibility = View.VISIBLE
                binding.writeAddress.visibility = View.GONE
                binding.findBtn.visibility = View.INVISIBLE
                binding.reFindBtn.visibility = View.VISIBLE
                binding.writeAddressTxt.text = it.address
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initEditText() {
        binding.writeAddress.setOnTextChanged { p0, _, _, _ ->
            setNextBtn(p0, binding.finishBtn)
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> activity?.findNavController(R.id.registerContainer)
                ?.popBackStack()
            R.id.findBtn -> {
                registerViewModel.getAddress(binding.writeAddress.text.toString())
                keyboardHide(requireActivity())
            }
            R.id.reFindBtn -> {
                binding.writeAddressTxt.visibility = View.GONE
                binding.writeAddress.visibility = View.VISIBLE
                binding.reFindBtn.visibility = View.GONE
                binding.findBtn.visibility = View.VISIBLE
                binding.writeDetailAddress.visibility = View.GONE
                binding.writeAddress.setText("")
                binding.writeAddress.requestFocus()
                keyboardShow(requireActivity())
            }
            R.id.finishBtn -> {
                registerViewModel.setAddress(binding.writeAddress.text.toString())
                registerViewModel.signUp()
                startActivity(
                    Intent(
                        context,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                )
                activity?.finish()
            }
        }
    }
}