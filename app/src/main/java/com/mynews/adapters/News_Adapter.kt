package com.mynews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mynews.R
import com.mynews.models.Article
import com.mynews.models.News_response

class News_Adapter:PagingDataAdapter<Article,News_Adapter.NewsViewHolder>(diffcallback) {
    lateinit var differ:Article


    class  NewsViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)

    companion object {
        var diffcallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }

    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

              var itemposition =  getItem(position)
        if (itemposition !=null){
            differ = itemposition
            holder.itemView.apply {
                findViewById<TextView>(R.id.rv_publish_at).text=itemposition.publishedAt
                findViewById<TextView>(R.id.rv_dscp).text=itemposition.description
                findViewById<TextView>(R.id.rv_title).text=itemposition.title
                findViewById<TextView>(R.id.rv_source).text=itemposition.source.name
                Glide.with(this).load(itemposition.urlToImage).into(findViewById(R.id.url_image))
                setOnClickListener{
                    onItemClickListner?.let {
                        it(itemposition)
                    }
                }

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       return NewsViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.news_item_view,parent,false)
       )
    }
    private var onItemClickListner:((Article)->Unit)?=null

    fun setOnItemClickListner(listner:(Article)->Unit){
       onItemClickListner =listner
    }


}