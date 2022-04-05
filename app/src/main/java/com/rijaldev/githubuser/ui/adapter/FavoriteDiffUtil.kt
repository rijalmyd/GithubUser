package com.rijaldev.githubuser.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity

class FavoriteDiffUtil(
    private val oldList: ArrayList<DetailUserEntity>,
    private val newList: List<DetailUserEntity>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].login != newList[newItemPosition].login -> false
            oldList[oldItemPosition].type != newList[newItemPosition].type -> false
            oldList[oldItemPosition].avatarUrl != newList[newItemPosition].avatarUrl -> false
            else -> true
        }
    }
}