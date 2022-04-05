package com.rijaldev.githubuser.ui.repository

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rijaldev.githubuser.R
import com.rijaldev.githubuser.data.source.local.entity.DetailRepoEntity
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import com.rijaldev.githubuser.data.source.remote.response.StatusResponse
import com.rijaldev.githubuser.databinding.FragmentDetailRepoBinding
import com.rijaldev.githubuser.utils.SnackBarExt.showSnackBar
import com.rijaldev.githubuser.utils.TextLoader.loadData
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setInvisible
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRepoFragment : Fragment() {

    private var _binding: FragmentDetailRepoBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailRepoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailRepoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        setHasOptionsMenu(true)
        setUpView()
    }

    private fun setUpView() {
        val username = DetailRepoFragmentArgs.fromBundle(arguments as Bundle).username
        val repoName = DetailRepoFragmentArgs.fromBundle(arguments as Bundle).repositoryName
        if (username != null && repoName != null) {
            viewModel.setData(username, repoName)
        }
        binding?.contentRepo?.root?.setInvisible()
        viewModel.getDetailRepository.observe(requireActivity(), observer)
    }

    private val observer = Observer<ApiResponse<DetailRepoEntity>> { detailRepo ->
        when (detailRepo.status) {
            StatusResponse.EMPTY -> {
                binding?.apply {
                    shimmer.setGone()
                    contentRepo.root.setVisible()
                }
            }
            StatusResponse.SUCCESS -> {
                binding?.apply {
                    shimmer.setGone()
                    contentRepo.root.setVisible()
                }
                if (detailRepo.body != null) {
                    populateUser(detailRepo.body)
                }
            }
            StatusResponse.ERROR -> {
                binding?.apply {
                    shimmer.setGone()
                    contentRepo.root.setVisible()
                    requireActivity().showSnackBar(requireActivity().window.decorView.rootView,
                        detailRepo.message)
                }
            }
        }
    }

    private fun populateUser(detailRepoEntity: DetailRepoEntity) {
        binding?.contentRepo?.apply {
            with(detailRepoEntity) {
                tvRepoName.loadData(name)
                repoUrl.loadData(fullName)
                tvStars.loadData(resources.getString(R.string.stars, stargazersCount?.toString()))
                tvForks.loadData(resources.getString(R.string.forks, forksCount?.toString()))
                tvLanguage.loadData(language)
                tvIssuesCount.loadData(openIssuesCount?.toString())
                tvWatchersCount.loadData(watchersCount?.toString())
                tvNetworkCount.loadData(networkCount?.toString())
                tvDescription.text = description ?: "No description provided."
                repoUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl))
                    startActivity(intent)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}