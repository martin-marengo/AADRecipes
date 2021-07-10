package com.mmarengo.android.recipes.ui

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mmarengo.android.recipes.R

fun ImageView.loadImageFromUrl(url: String) {

    val circularProgressDrawable = CircularProgressDrawable(context)
//    circularProgressDrawable.strokeWidth = 5f
//    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide
        .with(context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_image_not_supported_72)
        .into(this)
}
