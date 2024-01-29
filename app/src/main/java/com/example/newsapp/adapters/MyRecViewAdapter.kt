package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.models.Article

class MyRecViewAdapter(val mainActivity: MainActivity) :
    RecyclerView.Adapter<MyRecViewAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener
    val articles = mutableListOf<Article>()

    class MyViewHolder(itemview: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemview) {
        var txtTitle = itemview.findViewById<TextView>(R.id.newsTitle)
        var txtDesc = itemview.findViewById<TextView>(R.id.newsDesc)
        var imgNews = itemview.findViewById<ImageView>(R.id.newsImg)

        init {
            itemview.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mainActivity).inflate(R.layout.news_item_view, parent, false)
        return MyViewHolder(view , mListener)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitle.text = articles[position].title
        holder.txtDesc.text = articles[position].description
        Glide.with(mainActivity).load(articles[position].urlToImage).into(holder.imgNews)
    }

    fun setData(articles: List<Article>) {
        this.articles.addAll(articles)
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listener: onItemClickListener) {
        mListener = listener
    }

}
