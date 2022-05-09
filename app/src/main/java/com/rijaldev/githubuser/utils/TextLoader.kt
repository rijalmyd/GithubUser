package com.rijaldev.githubuser.utils

import android.widget.TextView
import com.rijaldev.githubuser.utils.ViewVisibilityUtil.setGone

object TextLoader {
    fun TextView.loadData(data: String?) {
        if (!data.isNullOrEmpty()) this.text = data else this.setGone()
    }
}