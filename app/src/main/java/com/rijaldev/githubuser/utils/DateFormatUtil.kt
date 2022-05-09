package com.rijaldev.githubuser.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {
    fun String.getTimeAgo(): String {
        val format = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT")

        val dateBefore = sdf.parse(this)
        val pastTime = dateBefore?.time as Long
        val diff = Calendar.getInstance().time.time - pastTime

        val oneSec = 1000L
        val oneMin = 60 * oneSec
        val oneHour: Long = 60 * oneMin
        val oneDay: Long = 24 * oneHour
        val oneMonth: Long = 30 * oneDay
        val oneYear: Long = 365 * oneDay

        val diffMin: Long = diff / oneMin
        val diffHours: Long = diff / oneHour
        val diffDays: Long = diff / oneDay
        val diffMonths: Long = diff / oneMonth
        val diffYears: Long = diff / oneYear

        return when {
            diffYears > 0 -> "Updated $diffYears years ago"
            diffMonths > 0 && diffYears < 1 -> "Updated ${(diffMonths - diffYears / 12)} months ago"
            diffDays > 0 && diffMonths < 1 -> "Updated ${(diffDays - diffMonths / 30)} days ago"
            diffHours > 0 && diffDays < 1 -> "Updated ${(diffHours - diffDays * 24)} hours ago"
            diffMin > 0 && diffHours < 1 -> "Updated ${(diffMin - diffHours * 60)} min ago"
            diffMin < 1 -> "Updated just now"
            else -> "Unknown"
        }
    }
}