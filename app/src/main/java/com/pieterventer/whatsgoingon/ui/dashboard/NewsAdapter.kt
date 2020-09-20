package com.pieterventer.whatsgoingon.ui.dashboard

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pieterventer.whatsgoingon.data.model.response.NewsItem
import com.pieterventer.whatsgoingon.ui.dashboard.NewsAdapter.NewsViewHolder.Companion.HEADLINE
import com.pieterventer.whatsgoingon.ui.dashboard.NewsAdapter.NewsViewHolder.Companion.SUB
import com.pieterventer.whatsgoingon.ui.dashboard.vh.HeadlineNewsViewHolder
import com.pieterventer.whatsgoingon.ui.dashboard.vh.SmallNewsHeadingViewHolder
import kotlinx.android.extensions.LayoutContainer

class NewsAdapter(private val callback: (NewsItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = arrayListOf<NewsItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when (holder) {
            is HeadlineNewsViewHolder -> holder.bind(item, callback)
            is SmallNewsHeadingViewHolder -> holder.bind(item, callback)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADLINE
            else -> SUB
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADLINE -> HeadlineNewsViewHolder.inflate(parent)
            SUB -> SmallNewsHeadingViewHolder.inflate(parent)

            else -> SmallNewsHeadingViewHolder.inflate(parent)
        }
    }


    override fun getItemCount(): Int = items.size

    class NewsViewHolder constructor(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        companion object {

            const val HEADLINE = 1
            const val SUB = 2
        }
    }
}