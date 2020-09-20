package com.pieterventer.whatsgoingon.ui.dashboard

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pieterventer.whatsgoingon.R
import com.pieterventer.whatsgoingon.data.model.response.NewsItem
import com.pieterventer.whatsgoingon.data.repository.NewsRepository
import com.pieterventer.whatsgoingon.data.repository.resource.Resource
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val currentLiveNews = MutableLiveData<ArrayList<NewsItem>>()

    fun retrieveLatestNews(context: Context, callback: (String) -> Unit) {
        viewModelScope.launch {
            val result = newsRepository.retrieveLatestNewsFeed()

            if (result is Resource.Success) {
                currentLiveNews.value = result.value.articles
            } else if (result is Resource.Failure) {
                callback.invoke(context.getString(R.string.default_error))
                Timber.e(result.errorMessage)
            }
        }
    }
}