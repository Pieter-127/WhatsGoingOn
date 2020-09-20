package com.pieterventer.whatsgoingon.data.repository

import com.pieterventer.whatsgoingon.data.model.response.NewsResponse
import com.pieterventer.whatsgoingon.data.repository.resource.Resource
import com.pieterventer.whatsgoingon.data.repository.resource.safeApiCall
import com.pieterventer.whatsgoingon.data.retrofit.NewsApi

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun retrieveLatestNewsFeed(): Resource<NewsResponse> {
        return safeApiCall {
            newsApi.getNewsFeed()
        }
    }
}