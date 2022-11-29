package com.example.lost_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.user.MyLostEntity
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.databinding.ItemAddressEntryBinding

class EntryAddressAdapter(private val itemClick: (RegisterViewModel.Address) -> Unit) :
    ListAdapter<RegisterViewModel.Address, EntryAddressAdapter.EntryAddressViewHolder>(
        diffUtil
    ) {
    class EntryAddressViewHolder(val binding: ItemAddressEntryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RegisterViewModel.Address, itemClick: (RegisterViewModel.Address) -> Unit) {
            binding.address = item
            if (item.address == "") {
                binding.roadNameTxt.text = "건물 정보가 없습니다."
            }
            binding.selectAddress.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryAddressViewHolder {
        return EntryAddressViewHolder(
            ItemAddressEntryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EntryAddressViewHolder, position: Int) {
        holder.bind(currentList[position], itemClick)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RegisterViewModel.Address>() {
            override fun areItemsTheSame(
                oldItem: RegisterViewModel.Address,
                newItem: RegisterViewModel.Address
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RegisterViewModel.Address,
                newItem: RegisterViewModel.Address
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}