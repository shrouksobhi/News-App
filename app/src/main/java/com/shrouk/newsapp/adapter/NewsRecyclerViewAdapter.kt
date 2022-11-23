package com.shrouk.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.newsapp.databinding.HomeRecyclerItemlistBinding
import com.shrouk.newsapp.interfaces.NewsOnClick
import com.shrouk.newsapp.model.Articles


class NewsRecyclerViewAdapter(
    var news: ArrayList<Articles>, var context: Context, var newsOnClick: NewsOnClick
) : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {
    //var onClickListener: NewsOnClick = newsOnClick

    class NewsViewHolder(val binding: HomeRecyclerItemlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articles: Articles, click: NewsOnClick) {
            binding.article = articles
            binding.onClickListener = click
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)

        val binding = HomeRecyclerItemlistBinding.inflate(view, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val _news = news[position]
        holder.bind(_news,  newsOnClick )
//        Glide.with(View(context)).load(_news.urlToImage).into(holder.image)
//        holder.description.text = _news.description
//        holder.publishedAt.text = _news.publishedAt
//        holder.itemView.setOnClickListener {
//            onClickListener.newsOnclick(_news)
//        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}