package com.booksearch.booksearch

import android.app.Application
import com.booksearch.booksearch.di.component.AppComponent
import com.booksearch.booksearch.di.component.DaggerAppComponent

class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        initDaggerGraph()
    }

    private fun initDaggerGraph() {
        appComponent = DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
        appComponent.inject(this)
    }

}