package com.rijaldev.githubuser.utils

import android.widget.TextView
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone

object TextLoader {
    fun TextView.loadData(data: String?) {
        data?.let {
            if (it.isNotEmpty()) this.text = it else this.setGone()
        } ?: run { this.setGone() }
    }
}