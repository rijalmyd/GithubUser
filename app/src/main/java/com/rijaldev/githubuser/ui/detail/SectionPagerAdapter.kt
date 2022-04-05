package com.rijaldev.githubuser.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rijaldev.githubuser.ui.detail.follow.FollowFragment
import com.rijaldev.githubuser.ui.detail.repo.RepoFragment

class SectionPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, username: String?): FragmentStateAdapter(fm, lifecycle) {

    private val username = username

    override fun getItemCount(): Int  = DetailFragment.TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RepoFragment()
            1 -> fragment = FollowFragment()
            2 -> fragment = FollowFragment()
        }
        fragment?.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_SECTION_NUMBER, position)
            putString(FollowFragment.EXTRA_USERNAME, username)
        }
        return fragment as Fragment
    }
}