package com.example.kittystore.ui.screen.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kittystore.databinding.StoreItemBinding
import com.example.kittystore.domain.model.StoreItem

class StoreAdapter : PagingDataAdapter<StoreItem, StoreAdapter.VH>(ItemComparator) {

    object ItemComparator : DiffUtil.ItemCallback<StoreItem>() {
        override fun areItemsTheSame(oldItem: StoreItem, newItem: StoreItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: StoreItem, newItem: StoreItem) =
            oldItem == newItem
    }

    class VH(
        private val binding: StoreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreItem) {
            binding.itemName.text = item.name

            binding.imageItem.load(item.imageUrl) {
                crossfade(true)
                placeholder(android.R.color.darker_gray)
            }
        }

        companion object {
            fun create(parent: ViewGroup): VH {
                val inflater = LayoutInflater.from(parent.context)
                val binding = StoreItemBinding.inflate(inflater, parent, false)
                return VH(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.create(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}