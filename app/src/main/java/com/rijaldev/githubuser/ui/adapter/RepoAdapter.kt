package com.rijaldev.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rijaldev.githubuser.R
import com.rijaldev.githubuser.data.local.entity.RepoEntity
import com.rijaldev.githubuser.databinding.ItemRepoBinding
import com.rijaldev.githubuser.utils.CountFormatUtil.toCountFormat
import com.rijaldev.githubuser.utils.TextLoader.loadData

class RepoAdapter(private val callback: OnRepoCallback
): ListAdapter<RepoEntity, RepoAdapter.RepoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val dataRepo = getItem(position)
        holder.bind(dataRepo)
    }

    inner class RepoViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepoEntity) {
            binding.apply {
                tvTitle.loadData(repo.name)
                tvVisibility.loadData(repo.visibility?.replaceFirstChar { it.uppercase() })
                tvDescription.loadData(repo.description ?: "-")
                tvLanguage.loadData(repo.language ?: itemView.context.getString(R.string.text_unknown))
                tvStars.loadData(repo.stargazersCount?.toCountFormat())
                itemView.setOnClickListener { callback.onItemClicked(repo) }
            }
        }
    }

    interface OnRepoCallback {
        fun onItemClicked(repoEntity: RepoEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoEntity>() {
            override fun areItemsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean =
                when {
                    oldItem.name != newItem.name -> false
                    oldItem.description != newItem.description -> false
                    oldItem.language != newItem.language -> false
                    oldItem.owner != newItem.owner -> false
                    oldItem.stargazersCount != newItem.stargazersCount -> false
                    else -> true
                }
        }
    }
}