package com.pieterventer.whatsgoingon.data.retrofit

import com.pieterventer.whatsgoingon.BuildConfig
import com.pieterventer.whatsgoingon.data.model.AvailableCountries
import com.pieterventer.whatsgoingon.data.model.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(value = "top-headlines")
    suspend fun getNewsFeed(
        @Query("apiKey") apiKey: String = BuildConfig.apiKey,
        @Query("country") countryFilter: String = AvailableCountries.Canada.code
    ): NewsResponse
}