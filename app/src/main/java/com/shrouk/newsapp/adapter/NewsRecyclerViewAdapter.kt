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


class NewsRecyclerViewAdapter (var news: ArrayList<Articles>
,var context :Context)
    : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>(){
    class NewsViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
     var image:ImageView=itemView.findViewById(R.id.imageview)
     var description: TextView =itemView.findViewById(R.id.body_text)
     var publishedAt:TextView=itemView.findViewById(R.id.publishedAt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_recycler_itemlist, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val _news = news[position]
        Glide.with(View(context)).load(_news.urlToImage).into(holder.image)
      holder.description.text=_news.description
        holder.publishedAt.text=_news.publishedAt
    }

    override fun getItemCount(): Int {
        return  news.size
    }
}