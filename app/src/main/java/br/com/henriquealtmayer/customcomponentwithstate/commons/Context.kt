package br.com.henriquealtmayer.customcomponentwithstate.commons

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(message: String) {
    showToast(message, Toast.LENGTH_SHORT)
}

fun Context.showLongToast(message: String) {
    showToast(message, Toast.LENGTH_LONG)
}

private fun Context.showToast(
    message: String,
    duration: Int
) {
    Toast.makeText(this, message, duration).show()
}