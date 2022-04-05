package com.rijaldev.githubuser.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rijaldev.githubuser.data.source.local.entity.RepoEntity

class RepoDiffUtil(private val oldList: ArrayList<RepoEntity>, private val newList: List<RepoEntity>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].visibility != newList[newItemPosition].visibility -> false
            oldList[oldItemPosition].description != newList[newItemPosition].description -> false
            oldList[oldItemPosition].language != newList[newItemPosition].language -> false
            oldList[oldItemPosition].stargazersCount != newList[newItemPosition].stargazersCount -> false
            else -> true
        }
    }
}