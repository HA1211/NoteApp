package com.nqh.noteapp

import android.icu.util.Calendar

object DateUtils {
    fun getDate(): String {
        val calendar = Calendar.getInstance()
        return String.format(
            "%02d/%02d/%04d   %02d:%02d:%02d",
            calendar.get(Calendar.DATE),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.HOUR),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        )
    }
}