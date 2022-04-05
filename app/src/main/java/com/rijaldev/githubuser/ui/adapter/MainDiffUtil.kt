package com.rijaldev.githubuser.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rijaldev.githubuser.data.source.local.entity.UserEntity

class MainDiffUtil(private val oldList: ArrayList<UserEntity>, private val newList: List<UserEntity>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].userId == newList[newItemPosition].userId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].login != newList[newItemPosition].login -> false
            oldList[oldItemPosition].type != newList[newItemPosition].type -> false
            oldList[oldItemPosition].avatarUrl != newList[newItemPosition].avatarUrl -> false
            else -> true
        }
    }
}