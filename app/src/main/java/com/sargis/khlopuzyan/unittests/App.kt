package com.sargis.khlopuzyan.unittests

import android.app.Application
import com.sargis.khlopuzyan.data.di.dataModule
import com.sargis.khlopuzyan.domain.di.domainModule
import com.sargis.khlopuzyan.presenter.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule + domainModule + presenterModule)
        }
    }
}