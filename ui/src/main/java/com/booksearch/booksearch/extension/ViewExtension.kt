package com.booksearch.booksearch.extension

import android.view.View

fun View.goneUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
