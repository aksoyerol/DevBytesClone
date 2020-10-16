package com.erolaksoy.devbytesclone.util

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erolaksoy.devbytesclone.R
import com.erolaksoy.devbytesclone.enums.Status
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("bindImage")
fun bindImage(image: ImageView, imgUrl: String) {
    val uri = imgUrl.toUri().buildUpon().scheme("https").build()
    imgUrl.let {
        Glide.with(image.context).load(uri).apply(RequestOptions().placeholder(R.drawable.custom_loading_image_animation)).into(image)
    }
}

@BindingAdapter("bindStatus")
fun bindStatus(progressBar: ProgressBar, status: Status) {
    return when (status) {
        Status.LOADING -> progressBar.isVisible = true
        Status.LOADED -> progressBar.isVisible = false
        else -> progressBar.isVisible = true
    }
}

@BindingAdapter("bindFormatDate")
fun bindFormatDate(txtView: TextView, date: String) {
    val timeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val offsetDateTime: OffsetDateTime =
        OffsetDateTime.parse(date, timeFormatter)
    val formattedDate = Date.from(Instant.from(offsetDateTime))
    txtView.text = formattedDate. toString()

}
