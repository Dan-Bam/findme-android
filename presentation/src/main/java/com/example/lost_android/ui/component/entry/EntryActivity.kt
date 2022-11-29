package com.example.lost_android.ui.component.entry

import androidx.activity.viewModels
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.viewmodel.DetailViewModel
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryActivity: BaseActivity<ActivityEntryBinding> (R.layout.activity_entry) {
    private val entryViewModel by viewModels<EntryViewModel>()
    private val detailViewModel by viewModels<DetailViewModel>()
    override fun createView() {
        entryViewModel.setTitle(intent.getStringExtra("type")!!)
        if (entryViewModel.title.value == getString(R.string.editLost) || entryViewModel.title.value == getString(R.string.editFound)) {
            if (entryViewModel.title.value == getString(R.string.editLost)) {
                detailViewModel.getDetail((intent.getStringExtra("id")!!))
            } else if (entryViewModel.title.value == getString(R.string.editFound)) {

            }
            supportFragmentManager.beginTransaction().replace(R.id.entryContainer, EntryFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.entryContainer, EntryCategoryFragment()).commit()
        }
    }
}