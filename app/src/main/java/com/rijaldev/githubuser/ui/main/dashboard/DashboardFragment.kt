package com.rijaldev.githubuser.ui.main.dashboard

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rijaldev.githubuser.R
import com.rijaldev.githubuser.data.local.entity.UserEntity
import com.rijaldev.githubuser.data.remote.response.Result
import com.rijaldev.githubuser.databinding.FragmentDashboardBinding
import com.rijaldev.githubuser.ui.adapter.UserAdapter
import com.rijaldev.githubuser.ui.main.MainViewModel
import com.rijaldev.githubuser.utils.SnackBarExt.showSnackBar
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setInvisible
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment: Fragment(), UserAdapter.UserClickCallback {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar)
        setHasOptionsMenu(true)
        setUpView()
    }

    private fun setUpView() {
        userAdapter = UserAdapter(this)
        binding?.apply {
            shimmer.root.setVisible()
            rvMain.setInvisible()
            rvMain.layoutManager = LinearLayoutManager(requireActivity())
            rvMain.setHasFixedSize(true)
            rvMain.adapter = userAdapter
        }
        viewModel.dataUser.observe(viewLifecycleOwner, observer)
    }

    private val observer = Observer<Result<List<UserEntity>>> { result ->
        when(result) {
            is Result.Success -> {
                binding?.apply {
                    shimmer.root.setGone()
                    rvMain.setVisible()
                }
                result.data?.let {
                    userAdapter.submitList(it)
                }
            }
            is Result.Error -> {
                binding?.apply {
                    shimmer.root.setGone()
                    rvMain.setVisible()
                    requireActivity().showSnackBar(
                        requireActivity().window.decorView.rootView,
                        result.message)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.cari)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding?.apply {
                        shimmer.root.setVisible()
                        rvMain.setInvisible()
                    }
                    viewModel.searchUser(query).observe(viewLifecycleOwner, observer)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isNotEmpty()) {
                    viewModel.searchUser(newText).observe(viewLifecycleOwner, observer)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View, user: UserEntity) {
        val toDetail = DashboardFragmentDirections.actionDashboardToDetailFragment()
        toDetail.username = user.login
        view.findNavController().navigate(toDetail)
    }
}