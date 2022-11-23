package com.shrouk.newsapp.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat

@BindingAdapter("formatDate")
fun TextView.setDate(date: String) {
    val outputDate: String?
    try {
        val curFormater = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        val postFormater = SimpleDateFormat("MMM dd, yyyy")

        val dateObj = curFormater.parse(date)
        outputDate = postFormater.format(dateObj)


        this.text = (outputDate)

    } catch (e: ParseException) {
        e.printStackTrace()
    }

}

@BindingAdapter("setDescription")
fun setDescription(view:TextView,description:String){
    view.text= description
}

@BindingAdapter("setImage")
fun AppCompatImageView.setImage(url:String){
    Glide.with(this.context).load(url).into(this)
}