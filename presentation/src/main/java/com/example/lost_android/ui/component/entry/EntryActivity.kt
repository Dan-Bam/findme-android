package com.example.lost_android.ui.component.entry

import com.example.lost_android.ui.base.BaseActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryActivity: BaseActivity<ActivityEntryBinding> (R.layout.activity_entry) {
    override fun createView() {
        supportFragmentManager.beginTransaction().replace(R.id.entryContainer, EntryCategoryFragment()).commit()
    }
}