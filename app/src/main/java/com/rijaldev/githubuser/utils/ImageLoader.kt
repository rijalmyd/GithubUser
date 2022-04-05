package com.rijaldev.githubuser.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.request.RequestOptions
import com.rijaldev.githubuser.R

object ImageLoader {
    fun ImageView.loadImage(context: Context, url: String?, vararg transformation : Transformation<Bitmap>) {
        transformation.forEach {
            val requestOptions = RequestOptions()
                .transform(it)
                .placeholder(R.color.image_loading)
            Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(this)
        }
    }
}