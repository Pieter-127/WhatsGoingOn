package com.pieterventer.whatsgoingon.di

import com.pieterventer.whatsgoingon.ui.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NewsViewModel(get()) }

}