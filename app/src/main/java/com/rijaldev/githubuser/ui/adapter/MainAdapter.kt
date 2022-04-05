package com.rijaldev.githubuser.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.rijaldev.githubuser.data.source.local.entity.UserEntity
import com.rijaldev.githubuser.databinding.ItemsUserBinding
import com.rijaldev.githubuser.utils.ImageLoader.loadImage

class MainAdapter(private val callback: UserClickCallback): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val listUser = ArrayList<UserEntity>()

    fun setUser(user: List<UserEntity>) {
        val diffUtil = MainDiffUtil(listUser, user)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listUser.clear()
        listUser.addAll(user)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemsUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = listUser.size

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
}