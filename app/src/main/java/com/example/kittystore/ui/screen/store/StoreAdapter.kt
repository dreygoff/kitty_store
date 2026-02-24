package com.example.kittystore.ui.screen.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kittystore.databinding.StoreItemBinding
import com.example.kittystore.domain.model.StoreItem


class StoreAdapter() : PagingDataAdapter<StoreItem, StoreAdapter.VH>(ItemComparator) {

    object ItemComparator : DiffUtil.ItemCallback<StoreItem>() {
        override fun areItemsTheSame(oldItem: StoreItem, newItem: StoreItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: StoreItem, newItem: StoreItem) =
            oldItem == newItem
    }

    class VH(val binding: StoreItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(StoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position) ?: return
        holder.binding.itemName.text = item.name

        holder.binding.imageItem.load(item.imageUrl) {
            crossfade(true)
            placeholder(android.R.color.darker_gray)
        }
    }
}