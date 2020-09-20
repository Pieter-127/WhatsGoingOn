package com.pieterventer.whatsgoingon.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun inflateView(@LayoutRes layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View =
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, attachToRoot)

fun Date.toTimeDisplayFormat(): String {
    val dateFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return try {
        dateFormatter.format(this)
    } catch (ex: Exception) {
        Timber.e(ex)
        dateFormatter.format(Calendar.getInstance(Locale.getDefault()))
    }
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}