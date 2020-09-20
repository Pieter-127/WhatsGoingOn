package com.pieterventer.whatsgoingon.di

import com.pieterventer.whatsgoingon.data.retrofit.RestClient
import org.koin.dsl.module

val apiModule = module {

    single { RestClient.NEWS_API }
}