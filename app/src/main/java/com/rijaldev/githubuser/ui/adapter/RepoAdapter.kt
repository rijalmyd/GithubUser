package com.rijaldev.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rijaldev.githubuser.data.source.local.entity.RepoEntity
import com.rijaldev.githubuser.databinding.ItemRepoBinding

class RepoAdapter(private val callback: OnRepoCallback): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    private val listRepo = ArrayList<RepoEntity>()

    fun setRepo(repo: List<RepoEntity>) {
        val diffUtil = RepoDiffUtil(listRepo, repo)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listRepo.clear()
        listRepo.addAll(repo)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val dataRepo = listRepo[position]
        holder.bind(dataRepo)
    }

    override fun getItemCount(): Int = listRepo.size

    inner class RepoViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepoEntity) {
            binding.apply {
                tvTitle.text = repo.name
                tvVisibility.text = repo.visibility?.replaceFirstChar { it.uppercase() }
                tvDescription.text = repo.description
                tvLanguage.text = repo.language
                tvStars.text = repo.stargazersCount.toString()
                itemView.setOnClickListener { callback.onItemClicked(it, repo) }
            }
        }
    }

    interface OnRepoCallback {
        fun onItemClicked(view: View, repoEntity: RepoEntity)
    }
}