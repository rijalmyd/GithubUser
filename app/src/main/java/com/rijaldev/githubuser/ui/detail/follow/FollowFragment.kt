package com.rijaldev.githubuser.ui.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rijaldev.githubuser.data.source.local.entity.UserEntity
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import com.rijaldev.githubuser.data.source.remote.response.StatusResponse
import com.rijaldev.githubuser.databinding.FragmentFollowBinding
import com.rijaldev.githubuser.ui.adapter.MainAdapter
import com.rijaldev.githubuser.ui.detail.DetailFragmentDirections
import com.rijaldev.githubuser.ui.detail.DetailViewModel
import com.rijaldev.githubuser.utils.SnackBarExt.showSnackBar
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowFragment : Fragment(), MainAdapter.UserClickCallback {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var followerAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val index: Int? = arguments?.getInt(ARG_SECTION_NUMBER, 1)
            val username = arguments?.getString(EXTRA_USERNAME)
            viewModel.setUsername(username.toString())
            if (index != null) {
                when (index) {
                    1 -> viewModel.getFollowers.observe(viewLifecycleOwner, observer)
                    2 -> viewModel.getFollowing.observe(viewLifecycleOwner, observer)
                }
            }
            followerAdapter = MainAdapter(this)
            binding?.rvFollower?.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                setHasFixedSize(true)
                adapter = followerAdapter
            }
        }
    }

    private val observer = Observer<ApiResponse<List<UserEntity>>> { listUser ->
        when (listUser.status) {
            StatusResponse.EMPTY -> {
                with(requireActivity()) {
                    showSnackBar(this.window.decorView.rootView, listUser.message)
                }
            }
            StatusResponse.SUCCESS -> {
                binding?.apply {
                    shimmer.root.setGone()
                    noUsers.root.setGone()
                }
                listUser?.body?.let {
                    if (it.isNotEmpty()) followerAdapter.setUser(it)
                    else binding?.noUsers?.root?.setVisible()
                }
            }
            StatusResponse.ERROR -> {
                binding?.apply {
                    shimmer.root.setGone()
                }
                with(requireActivity()) {
                    showSnackBar(this.window.decorView.rootView, listUser.message)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View, user: UserEntity) {
        val toDetail = DetailFragmentDirections.actionDetailFragmentSelf()
        toDetail.username = user.login
        view.findNavController().navigate(toDetail)
    }

    companion object {
        const val EXTRA_USERNAME = "username"
        const val ARG_SECTION_NUMBER = "section_number"
    }
}