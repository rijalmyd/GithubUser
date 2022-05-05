package com.rijaldev.githubuser.ui.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.rijaldev.githubuser.R

class MyTabLayout: TabLayout {

    private var firstTab: Drawable? = null
    private var secondTab: Drawable? = null
    private var thirdTab: Drawable? = null

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        firstTab = ContextCompat.getDrawable(context, R.drawable.ic_git)
        secondTab = ContextCompat.getDrawable(context, R.drawable.ic_follower)
        thirdTab = ContextCompat.getDrawable(context, R.drawable.ic_following)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        getTabAt(0)?.apply { icon = firstTab }
        getTabAt(1)?.apply { icon = secondTab }
        getTabAt(2)?.apply { icon = thirdTab }
    }
}