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
    private var indicator: Drawable? = null

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
        indicator = ContextCompat.getDrawable(context, R.drawable.ic_indicator_tab)
    }

    private fun Drawable?.setIconAt(position: Int) = getTabAt(position)?.apply { icon = this@setIconAt }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setSelectedTabIndicator(indicator)
        tabRippleColor = null
        firstTab.setIconAt(0)
        secondTab.setIconAt(1)
        thirdTab.setIconAt(2)
    }
}