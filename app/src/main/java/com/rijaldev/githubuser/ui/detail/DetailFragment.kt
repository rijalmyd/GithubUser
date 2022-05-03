package com.rijaldev.githubuser.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.rijaldev.githubuser.R
import com.rijaldev.githubuser.data.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.remote.response.Result
import com.rijaldev.githubuser.databinding.FragmentDetailBinding
import com.rijaldev.githubuser.databinding.LayoutBottomsheetBinding
import com.rijaldev.githubuser.utils.ImageLoader.loadImage
import com.rijaldev.githubuser.utils.SnackBarExt.showSnackBar
import com.rijaldev.githubuser.utils.TextLoader.loadData
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setInvisible
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailViewModel by viewModels()
    private var username: String? = null
    private var dataUser: DetailUserEntity? = null
    private var stateFavoriteUser: Boolean = false
    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        setHasOptionsMenu(true)
        setUpViewPager()
        setUpView()
    }

    private fun setUpViewPager() {
        binding?.apply {
            val dataUser = DetailFragmentArgs.fromBundle(arguments as Bundle).username
            if (dataUser != null) viewModel.setUsername(dataUser)
            username = dataUser
            val tabs = tabs
            val viewPager = viewPager
            val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager, lifecycle, dataUser)
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun setUpView() {
        if (username != null) {
            (activity as AppCompatActivity).supportActionBar?.title = username.toString()
            binding?.header?.root?.setInvisible()
            viewModel.detailUser .observe(viewLifecycleOwner, observer)
        }
    }

    private val observer = Observer<Result<DetailUserEntity>> { result ->
        when (result) {
            is Result.Success -> {
                binding?.apply {
                    shimmer.setGone()
                    header.root.setVisible()
                }
                dataUser = result.data
                dataUser?.let {
                    populateUser(it)
                    lifecycleScope.launch(Dispatchers.IO) {
                        val count = viewModel.isFavorite(it.userId)
                        withContext(Dispatchers.Main) {
                            stateFavoriteUser = count > 0
                            setFavoriteState(stateFavoriteUser)
                        }
                    }
                }
            }
            is Result.Error -> {
                binding?.apply {
                    shimmer.setGone()
                    header.root.setVisible()
                    requireActivity().showSnackBar(
                        requireActivity().window.decorView.rootView,
                        result.message)
                }
            }
        }
    }

    private fun populateUser(detailEntity: DetailUserEntity) {
        binding?.header?.apply {
            with(detailEntity) {
                ivUserProfile.loadImage(requireActivity(), avatarUrl, CircleCrop())
                tvRepository.loadData(publicRepos?.toString())
                tvFollowers.loadData(followers?.toString())
                tvFollowing.loadData(following?.toString())
                tvName.loadData(name)
                tvType.loadData(type)
                tvLocation.loadData(location)
                tvCompany.loadData(company)
                tvBio.loadData(bio?.trim())
                tvUrl.loadData(blog)
                val verifiedLink = blog?.verify()
                tvUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(verifiedLink))
                    startActivity(intent)
                }
            }
        }
    }

    private fun String.verify(): String {
        val rightLink : Boolean = this.contains("http")
        return if (rightLink) this else "https://$this"
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.btn_favorite)
        menuItem?.apply {
            icon = if (state) {
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_fav_fill)
            } else {
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_fav)
            }
        }
    }

    private fun setUpBottomSheet() {
        val dialog = BottomSheetDialog(requireActivity())
        val view = LayoutBottomsheetBinding.inflate(layoutInflater)
        dialog.setContentView(view.root)
        dialog.show()

        view.root.setOnClickListener {
            dialog.dismiss()
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Pengguna $username telah bergabung dengan github! Sekarang giliranmu"
                )
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Bagikan")
            startActivity(shareIntent)
        }
    }

    private fun onFavButtonClick() {
        stateFavoriteUser = if (stateFavoriteUser) {
            dataUser?.userId?.let { viewModel.removeFromFavorite(it) }
            requireActivity().showSnackBar(requireActivity().window.decorView.rootView,
                "List Favorite diperbarui ")
            setFavoriteState(false)
            false
        } else {
            dataUser?.let { viewModel.addToFavorite(it) }
            requireActivity().showSnackBar(requireActivity().window.decorView.rootView,
                "Menambahkan $username ke favorite")
            setFavoriteState(true)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        viewModel.detailUser.observe(viewLifecycleOwner, observer)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.btn_menu -> {
                setUpBottomSheet()
                true
            }
            R.id.btn_favorite -> {
                if (dataUser != null) onFavButtonClick()
                true
            }
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

    companion object {
        @StringRes
        val TAB_TITLES = arrayOf(R.string.repository, R.string.followers, R.string.following)
    }
}