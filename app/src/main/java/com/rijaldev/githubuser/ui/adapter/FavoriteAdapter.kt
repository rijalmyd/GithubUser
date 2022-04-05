package com.rijaldev.githubuser.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.databinding.ItemsUserBinding
import com.rijaldev.githubuser.utils.ImageLoader.loadImage

class FavoriteAdapter(private val callback: OnUserFavCallback)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val listUser = ArrayList<DetailUserEntity>()

    fun setUser(user: List<DetailUserEntity>) {
        val diffUtil = FavoriteDiffUtil(listUser, user)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listUser.clear()
        listUser.addAll(user)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): FavoriteViewHolder {
        val binding = ItemsUserBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = listUser.size

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
}