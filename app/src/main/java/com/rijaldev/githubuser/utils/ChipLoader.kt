package com.rijaldev.githubuser.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.rijaldev.githubuser.R

object ChipLoader {
    fun ChipGroup.addChip(context: Context, label: String) {
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isClickable = true
            isCheckedIconVisible = false
            isCheckable = false
            isFocusable = true
            chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.bg_splash_screen)
            chipStrokeWidth = 1.0f
            chipStrokeColor = ContextCompat.getColorStateList(context, R.color.grey)
            addView(this)
        }
    }
}