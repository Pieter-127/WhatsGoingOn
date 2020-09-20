package com.pieterventer.whatsgoingon.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pieterventer.whatsgoingon.R
import com.pieterventer.whatsgoingon.data.model.response.NewsItem
import com.pieterventer.whatsgoingon.util.inflateView
import com.pieterventer.whatsgoingon.util.toTimeDisplayFormat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var items = arrayListOf<NewsItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]

        val context = holder.containerView.context

        holder.containerView.articleTime.text = item.publishedAt.toTimeDisplayFormat()
        holder.containerView.articleHeading.text = item.title
        holder.containerView.author.text = item.author
        holder.containerView.articleSource.text = item.source.name ?: ""

        Glide.with(context).load(
            item.urlToImage
        ).centerCrop().placeholder(R.drawable.placeholder).into(holder.containerView.articleImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder.inflate(parent)

    override fun getItemCount(): Int = items.size

    class NewsViewHolder constructor(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        companion object {
            fun inflate(parent: ViewGroup):
                    NewsViewHolder =
                NewsViewHolder(inflateView(R.layout.news_item, parent, false))
        }
    }
}