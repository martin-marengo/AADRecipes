package com.mmarengo.android.recipes.ui

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mmarengo.android.recipes.R

fun ImageView.loadImageFromUrl(url: String) {
    Glide
        .with(context)
        .load(url)
        .error(R.drawable.thumb_error_indicator)
        .fallback(R.drawable.thumb_error_indicator)
        .placeholder(R.drawable.thumb_loading_indicator)
        .into(this)
}

fun Fragment.showKeyboard() {
    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(
            requireView(),
            InputMethodManager.SHOW_IMPLICIT
        )
}

fun Fragment.hideKeyboard() {
    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(
            requireView().windowToken,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
}
