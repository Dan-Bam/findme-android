package com.example.lost_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.databinding.ItemAddressBinding

class AddressAdapter(val itemList: List<RegisterViewModel.Address>, val itemClick: (RegisterViewModel.Address) -> Unit): RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    class AddressViewHolder(val binding: ItemAddressBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RegisterViewModel.Address, itemClick: (RegisterViewModel.Address) -> Unit) {
            binding.address = item
            binding.selectAddress.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            ItemAddressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(itemList[position], itemClick)
    }

    override fun getItemCount(): Int = itemList.size
}