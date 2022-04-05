package com.rijaldev.githubuser.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.databinding.FragmentFavoriteBinding
import com.rijaldev.githubuser.ui.adapter.FavoriteAdapter
import com.rijaldev.githubuser.ui.main.MainViewModel
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteAdapter.OnUserFavCallback {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var favAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favAdapter = FavoriteAdapter(this)
        viewModel.getFavUsers().observe(viewLifecycleOwner, observer)
    }

    private val observer = Observer<List<DetailUserEntity>> {
        binding?.shimmer?.root?.setGone()
        favAdapter.setUser(it)
        if (it.isEmpty()) binding?.noUsers?.setVisible()
        binding?.rvFav?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(view: View, user: DetailUserEntity) {
        val toDetail = FavoriteFragmentDirections.actionFavoriteToDetailFragment()
        toDetail.username = user.login
        view.findNavController().navigate(toDetail)
    }
}