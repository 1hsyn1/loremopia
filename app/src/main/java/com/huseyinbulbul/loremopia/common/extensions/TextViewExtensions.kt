package com.huseyinbulbul.loremopia.common.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("app:WriteTimestamp")
fun writeTimestamp(textView: TextView, timestamp: Long?){
    timestamp?.let {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy, EEE")
        val date = Date(timestamp * 1000)
        textView.text = dateFormat.format(date)
    } ?: run {
        textView.text = ""
    }
}