package com.rijaldev.githubuser.utils

import android.content.Context
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.rijaldev.githubuser.R

object LanguageColorUtil {
    fun TextView.setLeftDrawableColor(context: Context, language: String?) {
        val colorFilter: LightingColorFilter =
            when (language) {
                "Python" -> LightingColorFilter(Color.GREEN, Color.LTGRAY)
                "CSS", "C++", "Java" -> LightingColorFilter(Color.RED, Color.LTGRAY)
                "C", "PHP", "Dart", "Kotlin" -> LightingColorFilter(Color.BLUE, Color.LTGRAY)
                "Erlang" -> LightingColorFilter(Color.LTGRAY, Color.CYAN)
                "Go" -> LightingColorFilter(Color.WHITE, Color.LTGRAY)
                "HTML", "Swift" -> LightingColorFilter(Color.LTGRAY, Color.CYAN)
                "Shell" -> LightingColorFilter(Color.BLACK, Color.LTGRAY)
                "Ruby" -> LightingColorFilter(Color.CYAN, Color.LTGRAY)
                "TypeScript" -> LightingColorFilter(Color.MAGENTA, Color.LTGRAY)
                "JavaScript" -> LightingColorFilter(Color.YELLOW, Color.LTGRAY)
                else -> LightingColorFilter(Color.DKGRAY, Color.DKGRAY)
            }

        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_circle_24)
        drawable?.colorFilter = colorFilter

        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }
}