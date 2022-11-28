package com.example.lost_android.ui.component.entry

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import coil.api.load
import com.example.lost_android.ui.base.BaseFragment
import com.example.lost_android.util.checkPermission
import com.example.lost_android.util.getPath
import com.example.lost_android.util.setOnTextChanged
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEntryBinding
import java.io.File

class EntryFragment : BaseFragment<FragmentEntryBinding>(R.layout.fragment_entry) {
    private val entryViewModel by activityViewModels<EntryViewModel>()
    private var currentUri: Uri? = null
    private var file: File? = null

    override fun createView() {
        binding.entryFragment = this
        setTitle()
        isEntry()
        isData()
        binding.isSafeSwitch.setOnCheckedChangeListener { _, b ->
        }
    }

    private fun isEntry() {
        arrayOf(binding.writeTitle, binding.writeDescription).forEach {
            it.setOnTextChanged { p0, _, _, _ ->
                if (!binding.writeTitle.text.isNullOrBlank() && !binding.writeDescription.text.isNullOrBlank() && entryViewModel.currentAddress.value != null && entryViewModel.currentUri.value != null) {
                    binding.entryBtn.isEnabled = true
                    binding.entryBtn.isActivated = true
                } else {
                    binding.entryBtn.isEnabled = false
                    binding.entryBtn.isActivated = false
                }
            }
        }
    }

    private fun setTitle() {
        binding.titleTxt.text = entryViewModel.title.value
    }

    private fun isData() = entryViewModel.apply {
        if (params.value != null) {
            binding.writeTitle.setText(params.value!!["title"])
            binding.writeDescription.setText(params.value!!["description"])
        }
        if (title.value != getString(R.string.lostEntry)) with(binding) {
            imageEntry.text = getString(R.string.findImageEntry)
            imageEntryTxt.text = getString(R.string.findImageEntry)
            securityService.visibility = View.INVISIBLE
            whatSecurityService.visibility = View.INVISIBLE
            isSafeSwitch.visibility = View.INVISIBLE
        }
        if (currentAddress.value != null) {
            binding.selectLocateTxt.text = entryViewModel.currentAddress.value!!.address
            binding.selectedLocateLayout.visibility = View.VISIBLE
            binding.unSelectLocationLayout.visibility = View.GONE
        }
        if (currentUri.value != null) {
            binding.imageEntryHolder.load(entryViewModel.currentUri.value)
            binding.icImageEntry.visibility = View.GONE
            binding.imageEntryTxt.visibility = View.GONE
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.backBtn, R.id.backTxt -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.entryContainer, EntryCategoryFragment()).commit()
            R.id.chooseLocationBtn -> {
                entryViewModel.saveData(
                    binding.writeTitle.text.toString(),
                    binding.writeDescription.text.toString()
                )
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.entryContainer, EntryMapFragment()).commit()
            }
            R.id.imageEntryHolder -> {
                checkPermission(this, listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                startActivityForResult(
                    Intent(Intent.ACTION_PICK).setType(MediaStore.Images.Media.CONTENT_TYPE),
                    0
                )
            }
            R.id.entryBtn -> {
                entryViewModel.entryLost(
                    File(entryViewModel.currentUri.value!!.getPath(requireContext()))
                )
                requireActivity().finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                data?.data?.let {
                    entryViewModel.setUri(it)
                    binding.imageEntryHolder.load(it)
                    binding.icImageEntry.visibility = View.GONE
                    binding.imageEntryTxt.visibility = View.GONE
                }
            } else if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                Toast.makeText(context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}