package com.rijaldev.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.rijaldev.githubuser.data.local.entity.UserEntity
import com.rijaldev.githubuser.databinding.ItemsUserBinding
import com.rijaldev.githubuser.utils.ImageLoader.loadImage

class UserAdapter(private val callback: UserClickCallback)
    : ListAdapter<UserEntity, UserAdapter.MainViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemsUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class MainViewHolder(private val binding: ItemsUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                tvName.text = user.login
                tvUsername.text = user.type
                ivUser.loadImage(itemView.context, user.avatarUrl, CenterCrop(), RoundedCorners(16))
                itemView.setOnClickListener { callback.onClick(it, user) }
            }
        }
    }

    interface UserClickCallback {
        fun onClick(view: View, user: UserEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean =
                oldItem.userId == newItem.userId

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean =
                when {
                    oldItem.login != newItem.login -> false
                    oldItem.avatarUrl != newItem.avatarUrl -> false
                    oldItem.type != newItem.type -> false
                    else -> true
                }
        }
    }
}