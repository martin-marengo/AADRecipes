package com.mmarengo.android.recipes.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mmarengo.android.recipes.R


fun ImageView.loadImageFromUrl(url: String) {
    Glide
        .with(context)
        .load(url)
        .error(R.drawable.ic_image_not_supported_96)
        .placeholder(R.drawable.ic_hourglass_top_96)
        .into(this)
}
