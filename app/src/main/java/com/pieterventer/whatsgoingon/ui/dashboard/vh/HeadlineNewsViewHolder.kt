package com.pieterventer.whatsgoingon.ui.dashboard.vh

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pieterventer.whatsgoingon.R
import com.pieterventer.whatsgoingon.data.model.response.NewsItem
import com.pieterventer.whatsgoingon.util.inflateView
import com.pieterventer.whatsgoingon.util.toTimeDisplayFormat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.heading_news_item.view.*

class HeadlineNewsViewHolder constructor(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(newsItem: NewsItem, callback: (NewsItem) -> Unit) {

        val context = containerView.context

        containerView.articleTime.text = newsItem.publishedAt.toTimeDisplayFormat()
        containerView.articleHeading.text = newsItem.title
        containerView.author.text = newsItem.author ?: ""
        containerView.articleSource.text = newsItem.source.name ?: ""

        containerView.setOnClickListener {
            callback.invoke(newsItem)
        }

        Glide.with(context).load(newsItem.urlToImage).placeholder(R.drawable.placeholder)
            .centerCrop().into(containerView.headingImage)

    }

    companion object {
        fun inflate(
            parent: ViewGroup,
            @LayoutRes layoutRes: Int = R.layout.heading_news_item
        ): HeadlineNewsViewHolder {
            return HeadlineNewsViewHolder(inflateView(layoutRes, parent, false))
        }
    }
}