package com.rijaldev.githubuser.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.ln
import kotlin.math.pow

object CountFormatUtil {
    fun Int.toCountFormat(): String {
        val numberBefore = this.toDouble()
        val suffixChar = "KMGTPE"
        val formatter = DecimalFormat("###.#")
        formatter.roundingMode = RoundingMode.DOWN

        return if (numberBefore < 1000.0) formatter.format(numberBefore)
        else {
            val exp = (ln(numberBefore) / ln(1000.0)).toInt()
            val newFormat = formatter.format(numberBefore /
                    1000.0.pow(exp.toDouble())) + suffixChar[exp - 1]
            newFormat.replace(",", ".")
        }
    }
}