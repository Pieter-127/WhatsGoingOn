package com.pieterventer.whatsgoingon.data.repository

import com.pieterventer.whatsgoingon.data.model.response.NewsResponse
import com.pieterventer.whatsgoingon.data.repository.resource.Resource

interface NewsRepository {

    suspend fun retrieveLatestNewsFeed(): Resource<NewsResponse>
}