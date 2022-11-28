package com.example.lost_android.ui.component.entry

import androidx.activity.viewModels
import com.example.lost_android.ui.base.BaseActivity
import com.example.lost_android.viewmodel.EntryViewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryActivity: BaseActivity<ActivityEntryBinding> (R.layout.activity_entry) {
    private val entryViewModel by viewModels<EntryViewModel>()
    override fun createView() {
        entryViewModel.setTitle(intent.getStringExtra("type")!!)
        supportFragmentManager.beginTransaction().replace(R.id.entryContainer, EntryCategoryFragment()).commit()
    }
}