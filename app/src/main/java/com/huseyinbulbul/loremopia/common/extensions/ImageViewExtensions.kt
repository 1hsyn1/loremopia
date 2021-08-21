package com.dwarfplanet.newtunes.common

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:ImageUrl")
fun loadImageUrl(imageView: ImageView, url: String?){
    url?.let {
        Picasso.get()
            .load(it)
            .into(imageView)
    }
}