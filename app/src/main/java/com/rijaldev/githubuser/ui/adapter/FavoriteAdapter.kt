package com.rijaldev.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.rijaldev.githubuser.data.local.entity.DetailUserEntity
import com.rijaldev.githubuser.databinding.ItemsUserBinding
import com.rijaldev.githubuser.utils.ImageLoader.loadImage

class FavoriteAdapter(private val callback: OnUserFavCallback)
    : ListAdapter<DetailUserEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): FavoriteViewHolder {
        val binding = ItemsUserBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class FavoriteViewHolder(private val binding: ItemsUserBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DetailUserEntity) {
            binding.apply {
                tvName.text = user.login
                tvUsername.text = user.type
                ivUser.loadImage(itemView.context, user.avatarUrl,
                    CenterCrop(), RoundedCorners(16))
                itemView.setOnClickListener { callback.onItemClick(it, user) }
            }
        }
    }

    interface OnUserFavCallback {
        fun onItemClick(view: View, user: DetailUserEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailUserEntity>() {
            override fun areItemsTheSame(
                oldItem: DetailUserEntity,
                newItem: DetailUserEntity
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: DetailUserEntity,
                newItem: DetailUserEntity
            ): Boolean =
                when {
                    oldItem.id != newItem.id -> false
                    oldItem.avatarUrl != newItem.avatarUrl -> false
                    oldItem.login != newItem.login -> false
                    else -> true
                }
        }
    }
}