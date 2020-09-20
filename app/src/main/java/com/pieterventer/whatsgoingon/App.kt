package com.pieterventer.whatsgoingon

import android.app.Application
import com.pieterventer.whatsgoingon.di.apiModule
import com.pieterventer.whatsgoingon.di.repositoryModule
import com.pieterventer.whatsgoingon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, apiModule, repositoryModule))
        }
    }
}