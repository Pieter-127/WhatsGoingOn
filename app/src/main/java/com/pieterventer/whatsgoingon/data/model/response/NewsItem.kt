package com.pieterventer.whatsgoingon.data.model.response

import com.pieterventer.whatsgoingon.data.model.ArticleSource
import java.util.*

data class NewsItem(
    val source: ArticleSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String,
    val publishedAt: Date
)