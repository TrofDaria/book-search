package com.booksearch.booksearch.ui.extension

import android.util.Log
import android.widget.ImageView
import com.booksearch.booksearch.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImageByLink(link: String) {
    try {
        Glide.with(context)
            .load(link)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .into(this)
    } catch (throwable: Throwable) {
        Log.d("Glide", throwable.message, throwable)
    }
}