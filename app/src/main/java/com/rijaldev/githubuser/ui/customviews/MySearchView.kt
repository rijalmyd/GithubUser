package com.rijaldev.githubuser.ui.customviews

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.rijaldev.githubuser.R

class MySearchView: SearchView {

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
        findViewById<ImageView?>(androidx.appcompat.R.id.search_mag_icon).apply {
            adjustViewBounds = true
            maxWidth = MAX_WIDTH
            layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            setImageDrawable(null)
        }

        findViewById<LinearLayout>(androidx.appcompat.R.id.search_edit_frame).apply {
            val layoutPms = layoutParams as LinearLayout.LayoutParams
            layoutPms.leftMargin = LEFT_MARGIN
        }

        findViewById<EditText>(androidx.appcompat.R.id.search_src_text).apply {
            setHintTextColor(ContextCompat.getColor(context, R.color.grey))
            setOnFocusChangeListener { view, isFocused ->
                if (isFocused) {
                    val inputMethodManager = context
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    companion object {
        private const val LEFT_MARGIN = -16
        private const val MAX_WIDTH = 0
    }
}