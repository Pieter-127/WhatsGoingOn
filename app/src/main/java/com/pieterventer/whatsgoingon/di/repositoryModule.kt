package com.pieterventer.whatsgoingon.di

import com.pieterventer.whatsgoingon.data.repository.NewsRepository
import com.pieterventer.whatsgoingon.data.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<NewsRepository> { NewsRepositoryImpl(get()) }
}