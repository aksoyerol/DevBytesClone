package com.erolaksoy.devbytesclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erolaksoy.devbytesclone.databinding.ItemPlaylistRecyclerBinding
import com.erolaksoy.devbytesclone.domain.DevByteModel

class PlaylistAdapter() :
    ListAdapter<DevByteModel, PlaylistAdapter.MyViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(ItemPlaylistRecyclerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }
    class MyViewHolder(private val binding: ItemPlaylistRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(devByte : DevByteModel) {
            binding.devbytemodel = devByte
            binding.executePendingBindings()
        }
    }

}

class DiffUtilCallback : DiffUtil.ItemCallback<DevByteModel>() {
    override fun areItemsTheSame(oldItem: DevByteModel, newItem: DevByteModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DevByteModel, newItem: DevByteModel): Boolean {
        return oldItem.url == newItem.url
    }
}

