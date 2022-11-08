package com.shrouk.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.shrouk.newsapp.R
import com.shrouk.newsapp.model.Articles

class HomeViewPagerAdapter(val news:ArrayList<Articles>
                           , private var context: Context
): RecyclerView.Adapter<HomeViewPagerAdapter.ViewPagerViewHolder>(){



    class ViewPagerViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var image: ImageView = itemview.findViewById(R.id.image_view_pager)
        var textview:TextView=itemview.findViewById(R.id.topheadline_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
val view =LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
    return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
var currentnews=news[position]
        Glide.with(View(context)).load(currentnews.urlToImage).into(holder.image)
        holder.textview.text=currentnews.description
   }
    override fun getItemCount(): Int {
    return news.size
    }
}