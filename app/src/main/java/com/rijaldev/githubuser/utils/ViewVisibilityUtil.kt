package com.rijaldev.githubuser.utils

import android.view.View

object ViewVisibilityUtil {
    fun View.setVisible() {
        this.visibility = View.VISIBLE
    }

    fun View.setInvisible() {
        this.visibility = View.INVISIBLE
    }

    fun View.setGone() {
        this.visibility = View.GONE
    }
}